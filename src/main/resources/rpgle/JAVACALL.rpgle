     V*=====================================================================
     V* Description:
     V* This is a sample of an rpgle program calling a java program and print
     V* webservice response 
     V*===================================================================== 
     H/COPY QILEGEN,£INIZH 
      *--------------------------------------------------------------- 
     I/COPY QILEGEN,£TABB£1DS 
     I/COPY QILEGEN,£PDS 
      *--------------------------------------------------------------- 
      * Invoke Url 
      * . Function 
     D §§FUNZ          S             10 
      * . Method 
     D §§METO          S             10 
      * . Url 
     D §§SVAR          S           1000 
      *--------------------------------------------------------------- 
     D DSP             S             50 
      *--------------------------------------------------------------- 
     D* M A I N 
      *--------------------------------------------------------------- 
      * Initial settings 
     C                   EXSR      IMP0 
      * Call java program 
     C                   EXSR      FESE 
      * Final settings 
     C                   EXSR      FIN0 
      * End 
     C                   SETON                                        LR 
      *--------------------------------------------------------------- 
     C/COPY QILEGEN,£INZSR 
      *--------------------------------------------------------------- 
    RD* Initial subroutine (as *INZSR) 
      *--------------------------------------------------------------* 
     C     £INIZI        BEGSR 
      * 
     C                   ENDSR 
      *--------------------------------------------------------------* 
    RD* Initial settings 
      *--------------------------------------------------------------* 
     C     IMP0          BEGSR 
      * 
     C                   ENDSR 
      *--------------------------------------------------------------* 
    RD* Final settings 
      *--------------------------------------------------------------* 
     C     FIN0          BEGSR 
      * 
     C                   ENDSR 
      *--------------------------------------------------------------* 
    RD* Ese 
      *--------------------------------------------------------------* 
     C     FESE          BEGSR 
      * 
      * Invoke url 
     C                   EVAL      §§FUNZ='URL'                                 COSTANTE  
     C                   EVAL      §§METO='HTTP'                                COSTANTE 
     C                   EVAL      §§SVAR='http://www.mocky.io/v2/'+            COSTANTE 
     C                             '5185415ba171ea3a00704eed'                                     
      * 
     C                   CALL      'JD_URL' 
     C                   PARM                    §§FUNZ 
     C                   PARM                    §§METO 
     C                   PARM                    §§SVAR 
     C                   EVAL      DSP=§§SVAR
     C                   DSPLY                   DSP
      * 
     C                   ENDSR 
      *--------------------------------------------------------------* 
    RD* Detach 
      *--------------------------------------------------------------* 
     C     FCLO          BEGSR 
      * 
     C                   ENDSR 
