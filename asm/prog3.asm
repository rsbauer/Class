; Rob Bauer
; 12 FEB 98

; Program 3
; Emulate a hexadecimal RPN calculator.  Calculate hexadecimal numbers and
; binary operators.  To end program, press ENTER at a prompt.

; Error checking:  Verifies input is in range (0-9 a-f A-F H h + - & | ^).
;                  If not, error message is displayed.  Currently, program
;                  does not verify that the correct number of operands or
;                  operators are on the stack.


.MODEL SMALL
.STACK 100H
.DATA
        sCRLF           DB      13,10,'$'               ; CR, LF
        PROMPTCHAR      DB      '?'                     ; Prompt character
        CALC_ON         DB      'Calculator ON!$'       ; Calc is on
        CALC_OFF        DB      'Calculator OFF!$'      ; Calc is off
        FORLOOP         DW      0                       ; For loop counter
        INPUTCOUNTER    DB      0                       ; Input counter
        INVALID_FLAG    DB      0                       ; Invalid input flag
        ; Illegal digit string
        ILLEGAL         DB      'Illegal input character. Possibly used invalid digit or operand.',13,10,'Available characters: 0-9 A-F a-f H h + - & | ^$'
        

.CODE
        MAIN PROC
                                ; Initialize DS register
        MOV  AX,@DATA           ; Grab data segment address
        MOV  DS,AX              ; Store data segment in DS

        MOV AH,9                ; Display string function
        LEA DX,CALC_ON          ; Load calc on string
        INT 21H                 ; Display the string

; -- Begin THE_LOOP -----------------------------------------------------

    THE_LOOP:
        MOV INVALID_FLAG,0      ; Reset the invalid input flag
        MOV INPUTCOUNTER,0      ; Reset input counter to 0
        XOR BX,BX               ; Reset 

        CALL PROMPT             ; Show prompt

    GET_CHAR:
        CALL GETCHAR            ; Get a character from user (store in AL)
;        CALL CHAR_TEST          ; Test input character
        JMP CHAR_TEST


        JMP THE_LOOP            ; Jump to beginning 

; -- End THE_LOOP -------------------------------------------------------

    ENDPROGRAM:
        CALL CRLF               ; Move to a new line
        MOV AH,9                ; Display string function
        LEA DX,CALC_OFF         ; Load calc off string
        INT 21H                 ; Display the string

        MOV  AX,4C00H           ; Initialize AX register
        INT  21H                ; Tell DOS program is complete and retun to
                                ; system

        MAIN ENDP


     INPUT_DONE:   
        MOV AH,2                ; Display a character function
        MOV DL,10               ; Store the line feed character
        INT 21H                 ; Display the line feed character

        CMP INVALID_FLAG,1      ; Check if invalid flag was set or not
        JE ILLEGAL_INPUT        ; Jump if invalid flag is 1

        CMP INPUTCOUNTER,0      ; Check if COUNTER is 0
        JE ENDPROGRAM           ; User hit Enter - program done

        POP BX                  ; Pop 16bits
        CALL PRINT_HEX          ; Display hex number
        JMP THE_LOOP            ; Start from beginning


    PROMPT PROC
        CALL CRLF               ; Move to a new line
        MOV AH,2                ; Display a character function
        MOV DL,PROMPTCHAR       ; Store prompt
        INT 21H                 ; Show the prompt
        RET
    PROMPT ENDP

    CRLF PROC
        MOV AH,9                ; Display a string function
        LEA DX,sCRLF            ; Store carriage return and line feed
        INT 21H                 ; Display string
        RET
    CRLF ENDP

    GETCHAR PROC
        MOV AH,1                ; Get a character function
        INT 21H                 ; Get the character
        RET
    GETCHAR ENDP

    ILLEGAL_INPUT PROC
        MOV AH,9                ; Display string function
        LEA DX,ILLEGAL          ; Load illegal digit string
        INT 21H                 ; Display illegal digit string
        CALL CRLF               ; Display carraige return & line feed
        JMP THE_LOOP            ; Restart from the beginning
        RET                     ; <- Code should not be exicuted, but here in
                                ; case.
    ILLEGAL_INPUT ENDP

    CHAR_TEST:                  ; Test character entered by user stored in AL
        CMP AL,' '              ; Test if user entered a space (ignore if so)
        JE GET_CHAR             ; Get next character
        
        CMP AL,13               ; Test if user entered carriage return
        JE INPUT_DONE           ; Jump to end of program

        CMP AL,'H'              ; Test if user entered H for hex
        JE HEX                  ; Push the hex number on the stack

        CMP AL,'h'              ; Test if user entered h for hex
        JE HEX                  ; Push the hex number on the stack

        CMP AL,'+'              ; Test if user entered plus operator
        JE PLUS                 ; Add last 2 numbers on stack

        CMP AL,'-'              ; Test if user entred minus operator
        JE MINUS                ; Subtract last 2 numbers on stack

        CMP AL,'&'              ; Test if user entered bit-wise and
        JE bitAND               ; Perform bit-wise AND operation

        CMP AL,'|'              ; Test if user entered bit-wise or
        JE bitOR                ; Perform bit-wise OR operation

        CMP AL,'^'              ; Test if user entered bit-wise xor
        JE bitXOR               ; Perform bit-wise XOR operation

        CMP AL,'0'              ; Check if AL (user input) is less than zero
        JB INVALID_CHAR         ; Invalid char - set flag for invalid char

        CMP AL,'9'              ; Test if character is greater than 9
        JA CHECK_A_F            ; Character IS greater than 9-check if A-F
        AND AL,0Fh              ; Convert hex to binary
        JMP OK                  ; Character is OK

    HEX:
        PUSH BX                 ; Push 16bits onto the stack
        XOR BX,BX               ; Reset BX
        JMP GET_CHAR            ; Get the next character

     OK:
        ADD INPUTCOUNTER,1      ; Increment the input counter
        CALL SHIFT              ; Shift and add input
        JMP GET_CHAR            ; Get next character

    CHECK_A_F:
        CMP AL,'A'              ; Test if character is less than 'A'
        JB INVALID_CHAR         ; Invalid character

        CMP AL,'F'              ; Test if character is greater than 'F'
        JA CHECK_A_F_LOWER      ; Character is greater- check if a-f
        SUB AL,37H              ; Convert character to binary
        JMP OK

     CHECK_A_F_LOWER:
        CMP AL,'a'              ; Test if character is less than 'a'
        JB INVALID_CHAR         ; Invalid character- set flag for invalid char

        CMP AL,'f'              ; Test if charcter is greater than 'f'
        JA INVALID_CHAR         ; Character is invalid
        SUB AL,57H              ; Convert character to binary
        JMP OK                  ; Character is OK

     INVALID_CHAR:              
        MOV INVALID_FLAG,1      ; Set invalid character flag to 1
        JMP GET_CHAR            ; Go back and get next character


    PLUS:
        POP AX                  ; Get 1st hex number
        POP DX                  ; Get 2nd hex number
        ADD AX,DX               ; Add 2nd number to 1st number and store in AX
        PUSH AX                 ; Put answer back onto the stack
        JMP GET_CHAR

    MINUS:
        POP AX                  ; Get 1st hex number
        POP DX                  ; Get 2nd hex number
        SUB DX,AX               ; subtract 2nd number from 1st number
                                ; and store it in AX
        PUSH DX                 ; Put answer back onto the stack
        JMP GET_CHAR

    bitAND:
        POP AX                  ; Get 1st hex number
        POP DX                  ; Get 2nd hex number
        AND AX,DX               ; Perform bitwise operation
        PUSH AX                 ; Push answer back on to the stack
        JMP GET_CHAR

    bitOR:
        POP AX                  ; Get 1st hex number
        POP DX                  ; Get 2nd hex number
        OR AX,DX                ; Perform bitwise operation
        PUSH AX                 ; Push answer back on to the stack
        JMP GET_CHAR

    bitXOR:
        POP AX                  ; Get 1st hex number
        POP DX                  ; Get 2nd hex number
        XOR AX,DX               ; Perform bitwise operation
        PUSH AX                 ; Push answer back on to the stack
        JMP GET_CHAR

; -- END CHAR_TEST ------------------------------------------------------
;        RET
;    CHAR_TEST ENDP

    SHIFT PROC
        MOV CL,4                ; Get ready to shift 4 bits
        SHL BX,CL               ; Move 4 bits over so new hex digit can be added
        OR BL,AL                ; Put input into lower 4 bits
        RET
    SHIFT ENDP

    PRINT_HEX PROC
        MOV CX,4                ; Init counter
    LP:
        MOV FORLOOP,CX          ; Update FORLOOP counter
        MOV DL,BH               ; rotate nybbles around
        MOV CL,4                ; Want to shift 4 bits
        SHR DL,CL               ; Shift to the right 4 bits
        CMP DL,10               ; Test if DL is 10 or more (A-F)
        JL DECIMAL_DIGIT        ; Test if decimal or hex digit (jump if DL < 10)

        SUB DL,10               ; Do hex conversion & subtract 10 from number
        ADD DL,'A'              ; Add A value to get ASCII value
        JMP SHOW_IT             ; Display character

    DECIMAL_DIGIT:
        OR DL,30h               ; Convert to ASCII (logical conversion)
        JMP SHOW_IT             ; Display character

    SHOW_IT:
        MOV AH,2                ; Display character function
        INT 21H                 ; Display character
        MOV CL,4                ; Outside loop
        ROL BX,CL               ; Rotate BX
        MOV CX,FORLOOP          ; Copy FORLOOP counter to CX - continue for loop
        LOOP LP                 ; Start back at the beginning of the loop

        MOV AH,2                ; Display character function
        MOV DL,'h'              ; Store h for hex display
        INT 21H                 ; Display character

        RET
    PRINT_HEX ENDP

END MAIN
