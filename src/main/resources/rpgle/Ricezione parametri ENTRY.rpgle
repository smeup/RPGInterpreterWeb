     V*===================================================================== 
     V* Description:
     V* This is a sample of an rpgle program with "*ENTRY PLIST"  
     V*===================================================================== 
     H/COPY QILEGEN,£INIZH 
      *--------------------------------------------------------------- 
     I/COPY QILEGEN,£TABB£1DS 
     I/COPY QILEGEN,£PDS 
      *--------------------------------------------------------------- 
      * ENTRY 
      * . Function 
     D U$FUNZ          S             10 
      * . Method 
     D U$METO          S             10 
      * . String 
     D U$SVAR          S         210000 
      * . Return Code 
     D U$IN35          S              1 
      *--------------------------------------------------------------- 
     D DSP             S             50 
      *--------------------------------------------------------------- 
     D* M A I N 
      *--------------------------------------------------------------- 
     C     *ENTRY        PLIST 
     C                   PARM                    U$FUNZ 
     C                   PARM                    U$METO 
     C                   PARM                    U$SVAR 
     C                   PARM                    U$IN35 
      * 
     C                   EVAL      DSP='Entry parms received:'                  COSTANTE 
     C                   DSPLY                   DSP 
      * 
     C                   EVAL      DSP='U$FUNZ='+ U$FUNZ                        COSTANTE 
     C                   DSPLY                   DSP 
      * 
     C                   EVAL      DSP='U$METO='+ U$METO                        COSTANTE 
     C                   DSPLY                   DSP 
      * 
     C                   EVAL      DSP='U$SVAR='+ U$SVAR                        COSTANTE 
     C                   DSPLY                   DSP 
      * 
     C                   EVAL      DSP='U$IN35='+ U$IN35                        COSTANTE 
     C                   DSPLY                   DSP 
      * Initial settings 
     C                   EXSR      IMP0 
 
      * Final settings 
     C                   EXSR      FIN0 
      * End 
      * 
     C                   SETON                                        LR 
      *--------------------------------------------------------------- 
     C/COPY QILEGEN,£INZSR 
      *--------------------------------------------------------------- 
    RD* Initial subroutine (as *INZSR) 
      *--------------------------------------------------------------* 
     C     £INIZI        BEGSR 
      * 
      * 
     C                   ENDSR 
      *--------------------------------------------------------------* 
    RD* Initial settings 
      *--------------------------------------------------------------* 
     C     IMP0          BEGSR 
      * 
     C                   EVAL      DSP='IMP0 EXECUTED'                          COSTANTE 
     C                   DSPLY                   DSP 
      * 
     C                   ENDSR 
      *--------------------------------------------------------------* 
    RD* Final settings 
      *--------------------------------------------------------------* 
     C     FIN0          BEGSR 
      * 
     C                   EVAL      DSP='FIN0 EXECUTED'                          COSTANTE 
     C                   DSPLY                   DSP 
      * 
     C                   ENDSR 
      *--------------------------------------------------------------*