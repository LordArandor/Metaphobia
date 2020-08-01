;Cell used for creation of mazes;
Type Cell
Field cellType$ ;String to determine what type of cell it is and in turn what mesh to use
Field wn, ws, we, ww;Differemt Cell wall meshes
Field cnw, cne, csw, cse;Different Cell corner meshes
Field n,s,e,w ;Different Cell walls
Field cnnw, cnne, cnsw, cnse
Field x,y ;Cell position
Field cf ;Cell floor entity
Field cc ;Cell ceiling entity
Field is = 0; Is.
End Type

;COLLIDER VARIABLES
Global WALL_COLL = 1

;TEXTURE VARIABLES
Global WallTex = LoadTexture("Textures/wallpaper.jpg")
Global FloorTex = LoadTexture("Textures/carpet.jpg")
Global CeilingTex = LoadTexture("Textures/ceiling.jpg")
Global CornerTex = LoadTexture("Textures/column.jpg")

;MESH VARIABLES
Global cellWallNorthBase = CreateCube()
ScaleEntity cellWallNorthBase,scale_x,scale_Y,scale_z
EntityTexture cellWallNorthBase,WallTex
PositionEntity(CellWallNorthBase,0,0,0)
EntityType cellWallNorthBase,WALL_COLL
HideEntity cellWallNorthBase

Global cellWallEastBase = CreateCube()
RotateEntity cellWallEastBase,0,90,0
EntityTexture cellWallEastBase,WallTex
ScaleEntity cellWallEastBase,scale_x,scale_Y,scale_z
PositionEntity(CellWallEastBase,0,0,0)
EntityType cellWallEastBase,WALL_COLL
HideEntity cellWallEastBase

Global cellWallWestBase = CreateCube()
RotateEntity cellWallWestBase,0,-90,0
EntityTexture cellWallWestBase,WallTex
ScaleEntity cellWallWestBase,scale_x,scale_Y,scale_z
PositionEntity(CellWallWestBase,0,0,0)
EntityType cellWallWestBase,WALL_COLL
HideEntity cellWallWestBase

Global cellWallSouthBase = CreateCube()
RotateEntity cellWallSouthBase,0,180,0
EntityTexture cellWallSouthBase,WallTex
ScaleEntity cellWallSouthBase,scale_x,scale_Y,scale_z
PositionEntity(CellWallSouthBase,0,0,0)
EntityType cellWallSouthBase,WALL_COLL
HideEntity cellWallSouthBase

Global cellFloorBase = CreateCube()
RotateEntity cellFloorBase,90,0,0
EntityTexture cellFloorBase,FloorTex
ScaleEntity cellFloorBase,2.7,2.7,0.1
PositionEntity(CellFloorBase,0,0,0)
HideEntity cellFloorBase

Global cellCeilingBase = CreateCube()
RotateEntity cellCeilingBase,90,0,0
EntityTexture cellCeilingBase,CeilingTex
ScaleEntity cellCeilingBase,2.7,2.7,0.1
PositionEntity(CellCeilingBase,0,0,0)
HideEntity cellCeilingBase

Global cellCornerBase = CreateCube()
EntityTexture cellCornerBase,CornerTex
ScaleEntity cellCornerBase,0.2,2.7,0.2
PositionEntity(CellCornerBase,0,0,0)
HideEntity cellCornerBase

Function CreateCell.Cell(x,y,f,ct$)
	
	c.Cell = New Cell
	c\cellType = ct
	c\n = 1
	c\s = 1
	c\e = 1
	c\w = 1

	c\wn = CopyEntity(cellWallNorthBase)
	c\ws = CopyEntity(cellWallSouthBase)
	c\we = CopyEntity(cellWallEastBase)
	c\ww = CopyEntity(cellWallWestBase)
	
	c\cf = CopyEntity(CellFloorBase)

	c\x = x
	c\y = y

	HideEntity c\wn
	HideEntity c\ws
	HideEntity c\we
	HideEntity c\ww

 	If ct = "Four" Then 
		c\n = 0
		c\s = 0
		c\e = 0
		c\w = 0
		Return c
	EndIf

	If f = 1
		If ct = "Three"
			c\s = 0
			c\e = 0
			c\w = 0
		EndIf
		If ct = "Turn"
			c\s = 0
			c\e = 0
		EndIf 
		If ct = "Straight"
			c\n = 0
			c\s = 0
		EndIf
	EndIf

	If f = 2
		If ct = "Three"
			c\n = 0
			c\e = 0
			c\w = 0
		EndIf
		If ct = "Turn"
			c\s = 0
			c\w = 0
		EndIf 
		If ct = "Straight"
			c\e = 0
			c\w = 0
		EndIf
	EndIf

	If f = 3
		If ct = "Three"
			c\s = 0
			c\n = 0
			c\e = 0
		EndIf
		If ct = "Turn"
			c\n = 0
			c\e = 0
		EndIf 
	EndIf

	If f = 4
		If ct = "Three"
			c\s = 0
			c\n = 0
			c\w = 0
		EndIf
		If ct = "Turn"
			c\n = 0
			c\w = 0
		EndIf 
	EndIf

	s = 3

	PositionEntity(c\wn,x*s,0,y*s)
	PositionEntity(c\ws,x*s,0,y*s)
	PositionEntity(c\we,x*s,0,y*s)
	PositionEntity(c\ww,x*s,0,y*s)
			
	PositionEntity(c\cf,x*s,10,y*s-2.70)			

	If c\n = 1 Then ShowEntity c\wn
	If c\s = 1 Then ShowEntity c\ws
	If c\e = 1 Then ShowEntity c\we
	If c\w = 1 Then ShowEntity c\ww


	Return c
End Function

Function RndCell.Cell(x,y,e)
	a = Rnd(1,3)
	t = 0
	c.Cell = New Cell
	
	c\n = 0
	c\s = 0
	c\e = 0
	c\w = 0	

	c\cnnw = 1
	c\cnne = 1
	c\cnsw = 1
	c\cnse = 1	

	c\wn = CopyEntity(cellWallNorthBase)
	c\ws = CopyEntity(cellWallSouthBase)
	c\we = CopyEntity(cellWallEastBase)
	c\ww = CopyEntity(cellWallWestBase)

	c\cne = CopyEntity(cellCornerBase)
	c\cnw = CopyEntity(cellCornerBase)
	c\csw = CopyEntity(cellCornerBase)
	c\cse = CopyEntity(cellCornerBase)

	c\cf = CopyEntity(CellFloorBase)
	HideEntity(c\cf)

	c\cc = CopyEntity(CellCeilingBase)
	HideEntity(c\cc)

	c\x = x
	c\y = y

	HideEntity c\wn
	HideEntity c\ws
	HideEntity c\we
	HideEntity c\ww

	HideEntity c\cne
	HideEntity c\cnw
	HideEntity c\cse
	HideEntity c\csw

	For i = 1 To a Step 1
	t = Rnd(1,4)
	If t = 1 Then c\n = 1
	If t = 2 Then c\e = 1
	If t = 3 Then c\s = 1
	If t = 4 Then c\w = 1 
	Next

	If e = 1 Then c\n = 0
	If e = 2 Then c\e = 0
	If e = 3 Then c\s = 0
	If e = 4 Then c\w = 0
	
	s# = 5.40
	sf# = 5.40

	If c\n = 0 And c\w = 0 Then c\cnnw = 0
	If c\n = 0 And c\e = 0 Then c\cnne = 0
	If c\s = 0 And c\w = 0 Then c\cnsw = 0
	If c\s = 0 And c\e = 0 Then c\cnse = 0 

	PositionEntity(c\wn,x*s,2.7,y*s+2.7)
	PositionEntity(c\ws,x*s,2.7,y*s-2.7)
	PositionEntity(c\we,x*s+2.7,2.7,y*s)
	PositionEntity(c\ww,x*s-2.7,2.7,y*s)
	
	PositionEntity(c\cne,x*s+2.7,2.7,y*s+2.7)
	PositionEntity(c\cse,x*s-2.7,2.7,y*s-2.7)
	PositionEntity(c\csw,x*s+2.7,2.7,y*s-2.7)
	PositionEntity(c\cnw,x*s-2.7,2.7,y*s+2.7)
			
	PositionEntity(c\cf,x*sf,0,y*sf)
	PositionEntity(c\cc,x*sf,5.4,y*sf)
	
	is = 1
	Return c
End Function

Function ShowCell.Cell(c.Cell)
	If c\n = 1 Then ShowEntity c\wn
	If c\s = 1 Then ShowEntity c\ws
	If c\e = 1 Then ShowEntity c\we
	If c\w = 1 Then ShowEntity c\ww
	
	If c\cnnw = 1 Then ShowEntity c\cnw
	If c\cnne = 1 Then ShowEntity c\cne
	If c\cnse = 1 Then ShowEntity c\csw
	If c\cnsw = 1 Then ShowEntity c\cse

	ShowEntity c\cf
	ShowEntity c\cc
End Function

Function HideCell.Cell(c.Cell)
	If c\n = 1 Then HideEntity c\wn
	If c\s = 1 Then HideEntity c\ws
	If c\e = 1 Then HideEntity c\we
	If c\w = 1 Then HideEntity c\ww
	
	HideEntity c\cne
	HideEntity c\cnw
	HideEntity c\csw
	HideEntity c\cse

	HideEntity c\cf
	HideEntity c\cc
End Function