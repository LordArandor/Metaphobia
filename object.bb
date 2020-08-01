Type Lamp
Field mesh
Field light

;Textures

;Meshes
Global LampBase = LoadMesh("lamp.3ds")
EntityTexture LampBase,CornerTex
ScaleEntity LampBase,0.2,2.7,0.2
PositionEntity(LampBase,0,0,0)
HideEntity LampBase

Function CreateLamp.Lamp(x,y)
	l.Lamp = New Lamp()
End Function
	
