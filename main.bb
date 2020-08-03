Print("Metaphobia v0.0.5 - Deep Winter Studios")
.start
Input("Press Enter to start.")
Graphics3D 1920,1080,32,1
HidePointer 
SetBuffer BackBuffer()
AmbientLight 6,6,6
Print("Loading...")


Include "player.bb"
Include "cell.bb"
Include "object.bb"

SeedRnd MilliSecs()

;GLOBAL VARIABLES
Global player = CreatePivot()
ScaleEntity player,0.1,0.1,0.1
Global camera = CreateCamera(player)
Global flashlight = CreateLight(2,player)

Global sanity = 600
Global battery = 1200

Global flashlight_state = 0
Global fs_dead = 0

Global pcx%
Global pcy%
Global pmn,pme,pms,pmw

Global PLAY_COLL = 2

Const scale_x# = 2.7
Const scale_y# = 2.7
Const scale_z# = 0.1

Const map_size_x = 512
Const map_size_y = 512

Const max_draw_x = 4
Const max_draw_y = 4

;DEBUG STUFF
Global CompassTex = LoadTexture("Textures/compass.bmp")

Global Cell Dim mainmap.Cell(map_size_x+1,map_size_y+1) ;Maze array

Function LoadChunk()
	
	minx = pcx-max_draw_x
	miny = pcy-max_draw_y

	maxx = pcx+max_draw_x
	maxy = pcy+max_draw_y

	r = Rnd(1,4)

	For i = minx To maxx Step 1
		For j = miny To maxy Step 1
			If i > 0 And j > 0
				If mainmap(i,j) = Null Then mainmap(i,j) = RndCell(i,j,r)
				ShowCell(mainmap(i,j))
			EndIf	
		Next
	Next

End Function

Function ReloadChunk()
	
	minx = pcx-max_draw_x
	miny = pcy-max_draw_y

	maxx = pcx+max_draw_x
	maxy = pcy+max_draw_y

	r = Rnd(1,4)
	For i = minx To maxx Step 1
		For j = miny To maxy Step 1
			If i > 0 And j > 0
				DeleteCell(mainmap(i,j))
				Delete(mainmap(i,j))
				mainmap(i,j) = RndCell(i,j,r)
				ShowCell(mainmap(i,j))
				sanity = sanity - Rnd(1,2)
			EndIf	
		Next
	Next   

End Function

Function DeloadChunk()
	minx = pcx-max_draw_x
	miny = pcy-max_draw_y

	maxx = pcx+max_draw_x
	maxy = pcy+max_draw_y
	
	For i = 0 To map_size_x Step 1
		For j = 0 To map_size_y Step 1

			If i > 0 And j > 0
			If i < minx And mainmap(i,j) <> Null
			 	DeleteCell(mainmap(i,j))
				mainmap(i,j)\is = 0
				Delete(mainmap(i,j))
			EndIf
			If j < miny And mainmap(i,j) <> Null
			 	DeleteCell(mainmap(i,j))
				mainmap(i,j)\is = 0
				Delete(mainmap(i,j))
			EndIf
			If i > maxx And mainmap(i,j) <> Null
			 	DeleteCell(mainmap(i,j))
				mainmap(i,j)\is = 0
				Delete(mainmap(i,j))
			EndIf
			If j > maxy And mainmap(i,j) <> Null
			 	DeleteCell(mainmap(i,j))
				mainmap(i,j)\is = 0
				Delete(mainmap(i,j))
			EndIf
			EndIf
		Next
	Next

End Function

Function UpdatePlayerCellPosition()
	ux% = Floor(EntityX(player) / 5.4)
	uy% = Floor(EntityZ(player) / 5.4)

	pcx = ux
	pcy = uy
End Function

Function StatusCheck()
	p = 0

	If KeyHit(33)
		If flashlight_state = 0 And fs_dead = 0 And p = 0
		flashlight_state = 1
		LightRange flashlight,24
		p = 1
	EndIf

	If flashlight_state = 1 Or battery = 0 And p = 0
		flashlight_state = 0
		LightRange flashlight,0
		p = 1
	EndIf
	EndIf 

	If flashlight_state = 1 
		battery = battery - 1
		If sanity < 600 Then sanity = sanity + 1
	EndIf

	If flashlight_state = 0
		sanity = sanity - Rnd(1,3)
		If battery < 1200 Then battery = battery + 1
	EndIf 

	If battery = 0 
		fs_dead = 1
		flashlight_state = 0
	EndIf

	If battery = 600 Then fs_dead = 0

End Function

LightRange flashlight,0
LightConeAngles flashlight,0,80
LightColor flashlight,255,183,76

CameraFogMode camera,1
CameraFogRange camera,max_draw_x*36,max_draw_x*52
CameraFogColor camera,10,7,3
CameraClsColor camera,10,7,3

EntityType player,PLAY_COLL

Global compass = CreateCube()
RotateEntity compass,90,0,0
EntityTexture compass,CompassTex
ScaleEntity compass,2.7,2.7,0.1
PositionEntity(compass,-12,0,-12)

px = Floor(Rnd(1381,3000)/5.4)
py = Floor(Rnd(1381,2764)/5.4)

PositionEntity player,px,3,py

UpdatePlayerCellPosition()

LoadChunk()
DeloadChunk()
Collisions(PLAY_COLL,WALL_COLL,2,2)
;Input("Loading completed.")

fpsTimer = 0 
fps = 0
fpsTicks = 0
While Not KeyHit(1) Or sanity = 0
	UpdatePlayerCellPosition()
	LoadChunk()
	DeloadChunk()
	
	r = Rnd(500,1500)
	If MilliSecs() - fpsTimer > r Then StatusCheck()

	TurnCamera(camera,player,0.2)
	ControlPlayer(player)

	If (MilliSecs() - fpsTimer > 1000)
		fpsTimer = MilliSecs()
		fps = fpsTicks
		fpsTicks = 0
	Else
		fpsTicks = fpsTicks + 1
	EndIf

	If KeyHit(19) Then ReloadChunk()

	UpdateWorld
	RenderWorld
		Text 6,6,fps
		Text 6,24, "Sanity: " + Floor(sanity / 10)
		Text 6,36, "Battery: " + Floor(battery / 10)
		;Text 6,24,pcx
		;Text 6,36,pcy
		;Text 6,54,EntityX(player)
		;Text 6,66,EntityZ(player)
	Flip

Wend

End