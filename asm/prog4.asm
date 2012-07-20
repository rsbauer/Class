; Rob Bauer
; 20 March 98

; Program 4
; Procedure scans array and removes duplicates.  When done, procedure returns
; number of duplicates removed in AX.  Procedure expects the array's address
; and the number of items in the array.


.MODEL SMALL
.STACK 100H
.DATA

        array1 db 1,2,2,4
;        array1 db 4,1,6,4,6,3,6
        array1_size dw ($-array1)


.CODE
        MAIN PROC
                                ; Initialize DS register
        MOV  AX,@DATA           ; Grab data segment address
        MOV  DS,AX              ; Store data segment in DS

        XOR AX,AX
        MOV AX,ARRAY1_SIZE      ; store array size
        PUSH AX                 ; toss array size on stack
        LEA AX,ARRAY1           ; store address in AX
        PUSH AX                 ; toss onto stack

        CALL ELIM               ; eliminate duplicates

        CALL SHOWSTUFF          ; show AX and array

        MOV  AX,4C00H           ; Initialize AX register
        INT  21H                ; Tell DOS program is complete and return to
                                ; system
        MAIN ENDP


        SHOWSTUFF PROC

        LEA BX,ARRAY1
        XOR CX,CX
        MOV CX,ARRAY1_SIZE
        ADD CX,'0'
        MOV AH,2
        MOV DL,CL
        INT 21H

        MOV CX,ARRAY1_SIZE
        MOV SI,0

    THELOOP:
        MOV DL,[BX+SI]
        ADD DL,'0'
        INT 21H
        ADD SI,1
        LOOP THELOOP

        RET
        SHOWSTUFF ENDP

;----------------------------------------------------------------------------
        ELIM PROC
        ; Eliminate all duplicates and return in AX the total removed

        NUM EQU <WORD PTR [BP+6]>

        PUSH BP                 ; store current register values on stack
        MOV BP,SP               ; set base pointer to current stack pointer

        PUSH BX
        PUSH CX
        PUSH DX
        PUSH SI
        PUSH DI


;-- Test if array has only 0 or 1 elements ----------------------------------
        MOV CX,[BP+6]          ; Grab off stack the size of array
        DEC CX                  ; Want items-1 iterations
        JLE DONE                ; Check if size is <=0 = items <= 2 -> done
;-- End Test if array has only 0 or 1 elements ------------------------------

        MOV BX,[BP+4]           ; point to base of array
        MOV SI,0
        MOV DI,0

    LOOP_ONE:
    MOV SI,DI
    ADD SI,1

        LOOP_TWO:

                MOV DX,[BX+DI]

                CMP DX,[BX+SI]
                JBE EQUAL


        CONTINUE:
                ADD SI,1
                CMP SI,NUM
                JL LOOP_TWO

    INC DI
    CMP DI,NUM
    JNGE LOOP_ONE
    JMP DONE

    EQUAL:
        PUSH AX
        PUSH SI
;        DEC SI
                LOOP_THREE:

                        MOV AX,[BX+SI+1]
                        MOV [BX+SI],AX
;                        MOV [BX+SI+1], WORD PTR 0
                        INC SI

                CMP SI,NUM
                JL LOOP_THREE
                SUB NUM,1

        POP SI
;        SUB SI,1
;        DEC SI
        POP AX
        JMP CONTINUE

    DONE:


        MOV AX,NUM

        POP DI                  ; restore register values
        POP SI                  
        POP DX
        POP CX                  
        POP BX
        POP BP
        RET 4                   ; pop 4 bytes (function call info) & return

        ELIM ENDP
;----------------------------------------------------------------------------
END MAIN
