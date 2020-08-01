Function TurnCamera(camera,pivot,speed_multiplier#=1)
	TurnEntity pivot,0,-MouseXSpeed()*speed_multiplier,0
	TurnEntity camera,MouseYSpeed()*speed_multiplier,0,0
	RotateEntity camera,EntityPitch(camera),0,0
	
	If Not KeyDown(57) MoveMouse GraphicsWidth()/2,GraphicsHeight()/2
End Function

Function ControlPlayer(player)

;;Do we really need the ability to jump?

;	If KeyHit(57) And is_Jumping = False Then ;Jumping
;		is_Jumping = True
;		jumptime = 48
;	EndIf
;	
;	If is_Jumping = True And is_Jumping > 0 Then
;			If jumptime <= 16 Then 
;				MoveEntity(player,0,0.06,0) 
;			Else 
;				MoveEntity(player,0,0.09,0) 
;			EndIf
;		jumptime = jumptime - 1
;	EndIf
;
;	If jumptime = 0 Then
;		is_Jumping = False
;	EndIf
		
	If KeyDown(42) And stamina > 0 Then ; Sprinting

	If KeyDown(17) MoveEntity(player,0,0,0.06) ;Sprint Forward
	If KeyDown(31) MoveEntity(player,0,0,-0.06) ;Sprint Backward

	If KeyDown(32) MoveEntity(player,0.06,0,0) ;Sprint Right
	If KeyDown(30) MoveEntity(player,-0.06,0,0) ;Sprint Left

	is_Sprinting = True

	Else

	If KeyDown(17) MoveEntity(player,0,0,0.06) ;Walk Forward
	If KeyDown(31) MoveEntity(player,0,0,-0.06) ;Walk Backward

	If KeyDown(32) MoveEntity(player,0.06,0,0) ;Walk Right
	If KeyDown(30) MoveEntity(player,-0.06,0,0) ;Walk Left

	is_Sprinting = False

	EndIf

	If is_Sprinting = True 
		stamina=stamina-1
	ElseIf stamina = 0 And sprinting = True Then
		stamina=stamina-2
	ElseIf stamina < 100 And sprinting = False Then
		stamina=stamina+1
	EndIf
End Function

Function Flashlight()
	
	If KeyHit(33) And on = 0
	on = 1
	LightRange flashlight,24
	;Print("Flashlight on")
	EndIf
 
	If KeyHit(33) And on = 1
	on = 0
	LightRange flashlight,0
	;Print("Flashlight off")
	EndIf

	;Print (on)
End Function