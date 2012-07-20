; Rob Bauer
; 8 FEB 98

; Program 2
; Accept ONLY hex characters (0-9, a-f, A-F).  Hex numbers can only contain
; up to 8 characters.  If hex number meets previous specifications, program
; responds with the number of characters entered.  If any of the
; characters do not meet the specifications or an invalid number of characters
; has been entered then the program will inform the user that the input is 
; invalid and why. 

.MODEL  SMALL
.STACK  100H
.DATA

        VALIDINPUT      DB      'Correct input $'
        DIGITS          DB      ' digits.$'
        ILLEGAL         DB      'Illegal digit!$'
        TOOMANY         DB      'Too many digits!$'
        PROMPT          DB      '>$'
        TERMINATED      DB      'Program terminated at user''s request.$'
        INVALID_FLAG    DB      0       ; Invalid character flag
        COUNTER         DB      0       ; Set character counter to zero
        CRLF            DB      13,10,'$'  ; Store carriage return & line feed

.CODE

        MAIN PROC
                                ; Initialize DS register        
        MOV AX,@DATA            ; Grab data segment address
        MOV DS, AX              ; Store data segment in DS

    NEW_PROMPT:
        MOV COUNTER,0           ; reset counter - needed for looping
        MOV INVALID_FLAG,0      ; Set Invalid char flag to 0
        MOV AH,9                ; Display a string function
        LEA DX,PROMPT           ; Store the prompt
        INT 21H                 ; Display the prompt

;-------------------------------------------------------------------
; Character input & testing loop

    GET_CHAR:
        MOV AH,1                ; Get a character function
        INT 21H                 ; Get the character (store it in AL)

    TEST_CHAR:

        CMP AL,'%'              ; Check if user entered % to terminate prog.
        JZ TEST_THE_END         ; Go to the end of the program

        CMP AL,13               ; Check if user entered carriage return
        JE INPUT_DONE           ; User pressed enter - process info 

        CMP AL,'0'              ; Check if AL (user input) is less than zero
        JB INVALID_CHAR         ; Invalid character- set flag for invalid char

        CMP AL,'9'              ; Test if character is greater than 9
        JA CHECK_A_F            ; Character IS greater than 9-check if A-F
        JMP OK                  ; Character is ok

    CHECK_A_F:
        CMP AL,'A'              ; Test if character is less than 'A'
        JB INVALID_CHAR         ; Invalid character- set flag for invalid char

        CMP AL,'F'              ; Test if character is greater than 'F'
        JA CHECK_A_F_LOWER      ; Character is greater- check if a-f
        JMP OK                  ; Character is ok

    CHECK_A_F_LOWER:
        CMP AL,'a'              ; Test if character is less than 'a'
        JB INVALID_CHAR         ; Invalid character- set flag for invalid char

        CMP AL,'f'              ; Test if character is greater than 'f'
        JA INVALID_CHAR         ; Character is invalid
        JMP OK                  ; Character is ok

    OK:
        ADD COUNTER,1           ; Increment the counter by 1
        JMP GET_CHAR            ; Get next character

;-------------------------------------------------------------------
; Character Tests

    TEST_THE_END:
        CMP COUNTER,0           ; Check if counter is zero
        JZ THE_END              ; Counter is 0 so terminate program
        JMP INVALID_CHAR        ; Not 1st character so its an invalid character

    INVALID_CHAR:
        MOV INVALID_FLAG,1      ; Set invalid character flag to 1
        JMP GET_CHAR            ; Go back and get the next character

    INPUT_DONE:
        MOV AH,2                ; Display a character function
        MOV DL,10               ; Store the line feed character
        INT 21h                 ; Display the line feed character

        MOV AH,9                ; String output function

        CMP INVALID_FLAG,1      ; Check if invalid flag was set or not
        JE ILLEGAL_INPUT        ; Jump if invalid flag is 0

        CMP COUNTER, 8          ; Check if COUNTER is less than or equal to 8
        JA TOO_MANY_DIGITS      ; Too many digits

        LEA DX,VALIDINPUT       ; Load valid input string
        INT 21h                 ; Display string
        JMP DIGIT_COUNT         ; Jump to digit counter

     ILLEGAL_INPUT:
        LEA DX,ILLEGAL          ; Load illegal input string
        INT 21h                 ; Display string

        MOV AH,9                ; Display string function
        LEA DX,CRLF             ; Load carriage return and line feed
        INT 21h                 ; Display carraige return and line feed
        JMP NEW_PROMPT          ; Display a new prompt and start over

     DIGIT_COUNT:
        ADD COUNTER,'0'         ; Convert COUNTER to ASCII character
        MOV AH,2                ; Display character function
        MOV DL, COUNTER         ; Load the converted COUNTER
        INT 21h                 ; Display the COUNTER

        MOV AH,9                ; Display string function
        LEA DX,DIGITS           ; Load DIGITS
        INT 21h                 ; Display the string digits

        MOV AH,9                ; Display string function
        LEA DX,CRLF             ; Load carriage return and line feed
        INT 21h                 ; Display carraige return and line feed
        JMP NEW_PROMPT          ; Display a new prompt and start over

    TOO_MANY_DIGITS:
        MOV AH,9                ; Display string function
        LEA DX,TOOMANY          ; Load too many digits string
        INT 21h                 ; Display too many digits string

        LEA DX,CRLF             ; Load carriage return and line feed
        INT 21h                 ; Display carraige return and line feed

        JMP NEW_PROMPT          ; Display a new prompt and start over

;-------------------------------------------------------------------
; Only way out of program. Display terminated string and clean up memory for
; DOS

    THE_END:
        MOV AH,9                ; Display string function
        LEA DX,CRLF             ; Load carriage return and line feed
        INT 21h                 ; Display carraige return and line feed

        MOV AH,9                ; String function
        LEA DX,TERMINATED       ; Load the terminate string
        INT 21h                 ; Display the string

        MOV AX,4C00H            ; Initialize AX register
        INT 21H                 ; Tell DOS that program is complete
        
        MAIN ENDP

END MAIN

