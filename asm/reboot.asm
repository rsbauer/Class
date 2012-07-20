; Rob Bauer
; 1 FEB 98

; Program 1
; Accept 2 digits and add them then display the results


.MODEL SMALL
.STACK 100H
.DATA

.CODE
        MAIN PROC
                                ; Initialize DS register
        MOV  AX,@DATA           ; Grab data segment address
        MOV  DS,AX              ; Store data segment in DS


        INT 19H                 ; reboot the machine

        MOV  AX,4C00H           ; Initialize AX register
        INT  21H                ; Tell DOS program is complete and retun to
                                ; system

        MAIN ENDP
END MAIN
