Type Lamp
Field mesh
Field light
End Type 

Type Table
Field mesh
Field tlamp.Lamp
End Type


Type 



;Textures
LampTex = LoadTexture("Textures/lamp.jpg",256)
TableTex = LoadTexture("Textures/table.jpg",256)

;Meshes
Global LampBase = LoadMesh("Meshes/lamp.3ds")
EntityTexture LampBase,LampTex
ScaleEntity LampBase,0.20,0.20,0.20
PositionEntity(LampBase,0,0,0)
HideEntity LampBase

Global TableBase = LoadMesh("Meshes/table.3ds")
EntityTexture TableBase,TableTex
ScaleEntity TableBase,0.25,0.25,0.25
PositionEntity(TableBase,0,0,0)
HideEntity TableBase

Global LightBase = CreateLight(2)
LightRange LightBase,12
LightColor LightBase,255,183,76
PositionEntity LightBase,0,0,0

Function RndLamp.Lamp(x,y,f)
	l.Lamp = New Lamp
	l\mesh = CopyEntity(LampBase)

	s# = 5.40

	If f = 1 
		PositionEntity(l\mesh,x*s-1,1.1,y*s+2.4)
		RotateEntity(l\mesh,0,0,0)
	EndIf
	If f = 2
		PositionEntity(l\mesh,x*s+2.4,1.1,y*s+1)
		RotateEntity(l\mesh,0,-90,0)
	EndIf
	If f = 3
		PositionEntity(l\mesh,x*s-2.4,1.1,y*s-1)
		RotateEntity(l\mesh,0,90,0)
	EndIf
	If f = 4
		PositionEntity(l\mesh,x*s+1,1.1,y*s-2.4)
		RotateEntity(l\mesh,0,180,0)
	EndIf

	l\light = CreateLight(2,l\mesh)
	LightRange l\light,8
	LightColor l\light,255,183,76
	ScaleEntity l\light,0.5,0.5,0.5

	TranslateEntity l\light,3.4,6,-1.7

	Return l
End Function
	
Function RndTable.Table(x,y)
	t.Table = New Table

	t\mesh = CopyEntity(TableBase)
	t\tlamp = New Lamp
	
	s# = 5.40
	a = Rnd(1,4)
	If a = 1 
		PositionEntity(t\mesh,x*s,1,y*s+2)
		RotateEntity(t\mesh,0,0,0)
	EndIf
	If a = 2
		PositionEntity(t\mesh,x*s+2,1,y*s)
		RotateEntity(t\mesh,0,-90,0)
	EndIf
	If a = 3
		PositionEntity(t\mesh,x*s-2,1,y*s)
		RotateEntity(t\mesh,0,90,0)
	EndIf
	If a = 4
		PositionEntity(t\mesh,x*s,1,y*s-2)
		RotateEntity(t\mesh,0,180,0)
	EndIf

	lx = x
	ly = y

	t\tlamp = RndLamp(lx,ly,a)

	Return t
End Function 

Function DeleteTable(t.Table)
	HideEntity t\mesh
	FreeEntity t\mesh
	HideEntity t\tlamp\mesh
	FreeEntity t\tlamp\mesh
	Delete t\tlamp 
End Function