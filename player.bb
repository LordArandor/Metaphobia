Function TurnCamera(camera,pivot,speed_multiplier#=1)
	TurnEntity pivot,0,-MouseXSpeed()*speed_multiplier,0
	TurnEntity camera,MouseYSpeed()*speed_multiplier,0,0
	RotateEntity camera,EntityPitch(camera),0,0
	
	If Not KeyDown(57) MoveMouse GraphicsWidth()/2,GraphicsHeight()/2
End Function

Function ControlPlayer(player)

	If KeyDown(17) 
		MoveEntity(player,0,0,player_Speed) ;Walk Forward
		MoveEntity(player,0,bob_add,0)
	EndIf


	If KeyDown(31) 
		MoveEntity(player,0,0,-player_Speed) ;Walk Backward
		MoveEntity(player,0,bob_add,0)
	EndIf


	If KeyDown(32) 	
		MoveEntity(player,player_Speed,0,0) ;Walk Right
		MoveEntity(player,0,bob_add,0)
	EndIf


	If KeyDown(30) 
		MoveEntity(player,-player_Speed,0,0) ;Walk Left
		MoveEntity(player,0,bob_add,0)
	EndIf


	If bob_amt < (Pi * -0.08) bob_dir = True
	If bob_amt > (Pi * 0.08)  bob_dir = False 
	
	If bob_dir = False 
		bob_amt = bob_amt - 0.04
		bob_add = Sin(bob_amt)
	EndIf
	If bob_dir = True
		bob_amt = bob_amt + 0.04
		bob_add = Sin(bob_amt)
	EndIf

End Function