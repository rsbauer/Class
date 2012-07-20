; Rob Bauer
; 1 FEB 98

; Program 1
; Accept 2 digits and add them then display the results


.MODEL SMALL
.STACK 100H
.DATA
        FIRST   DB      ?       ; Contain the first digit
        SECOND  DB      ?       ; Contain the second digit
        CRLF    DB      13,10,'$'       ; Define carriage return & line feed
        THESUM  DB      'The sum of $'  ; Output string
        THEAND  DB      ' and $'        ; Output string for the and
        THEIS   DB      ' is $'         ; Output for the is

.CODE
        MAIN PROC
                                ; Initialize DS register
        MOV  AX,@DATA           ; Grab data segment address
        MOV  DS,AX              ; Store data segment in DS
                 
        MOV  AH,2               ; Display a character function
        MOV  DL,'?'             ; Character is ?
        INT  21H                ; Display the character

        MOV  AH,1               ; Get a character function

        INT  21H                ; Get the character and store in AL
        MOV  FIRST,AL           ; Store character in FIRST digit

        INT  21H                ; Get the character and store in AL
        MOV  SECOND,AL          ; Store character in SECOND digit

        MOV  AH,9               ; Display a string function
        LEA  DX,CRLF            ; Load address of CRLF into DX
        INT  21H                ; Display the string
        LEA  DX,THESUM          ; Load address of THESUM into DX
        INT  21H                ; Display the string

        MOV  AH,2               ; Display a character function
        MOV  DL,FIRST           ; Display FIRST digit
        INT  21H                ; Display the character

        MOV  AH,9               ; Display a string function
        LEA  DX,THEAND          ; Load address of THEAND into DX
        INT  21H                ; Display the string

        MOV  AH,2               ; Display a character function
        MOV  DL,SECOND          ; Display SECOND digit
        INT  21H                ; Display the character

        MOV  AH,9               ; Display a string function
        LEA  DX,THEIS           ; Load address of THEIS into DX
        INT  21H                ; Display the string

        ; Calculate the sum of FIRST and SECOND digits, then display
        MOV  BL,FIRST           ; Store FIRST 
        ADD  BL,SECOND          ; Add first and second together
        SUB  BL,48              ; Adjust answer so it is in ASCII
        MOV  AH,2               ; Display a character function
        MOV  DL,BL              ; Move value of sum so it can be displayed
        INT  21H                ; Display the sum

        MOV  AH,2               ; Display a character
        MOV  DL,'.'             ; Store the '.'
        INT  21H                ; Display the character

        MOV  AX,4C00H           ; Initialize AX register
        INT  21H                ; Tell DOS program is complete and retun to
                                ; system

        MAIN ENDP
END MAIN
