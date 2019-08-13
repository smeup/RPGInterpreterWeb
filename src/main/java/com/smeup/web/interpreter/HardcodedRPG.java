package com.smeup.web.interpreter;

public enum HardcodedRPG {
	
    HELLO_WORLD("﻿     D Msg             S             50\n" + 
			"     C                   Eval      Msg  = 'Hello World !'\n" + 
			"     C                   dsply                   Msg\n" + 
			"     C                   SETON                                          LR"),
    
    ENTRY_PLIST("     V*=====================================================================\n" + 
    		"     V* Date      Release Au Description\n" + 
    		"     V* dd/mm/yy  V5R1    AD Anonymous Developer\n" + 
    		"     V*=====================================================================\n" + 
    		"     H/COPY QILEGEN,£INIZH\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     I/COPY QILEGEN,£TABB£1DS\n" + 
    		"     I/COPY QILEGEN,£PDS\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"      * ENTRY\n" + 
    		"      * . Function\n" + 
    		"     D U$FUNZ          S             10\n" + 
    		"      * . Method\n" + 
    		"     D U$METO          S             10\n" + 
    		"      * . String\n" + 
    		"     D U$SVAR          S         210000\n" + 
    		"      * . Return Code\n" + 
    		"     D U$IN35          S              1\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     D DSP             S             50\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     D* M A I N\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     C     *ENTRY        PLIST\n" + 
    		"     C                   PARM                    U$FUNZ\n" + 
    		"     C                   PARM                    U$METO\n" + 
    		"     C                   PARM                    U$SVAR\n" + 
    		"     C                   PARM                    U$IN35\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='Entry parms received:'                  COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='U$FUNZ='+ U$FUNZ                        COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='U$METO='+ U$METO                        COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='U$SVAR='+ U$SVAR                        COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='U$IN35='+ U$IN35                        COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      * Initial settings\n" + 
    		"     C                   EXSR      IMP0\n" + 
    		"\n" + 
    		"      * Final settings\n" + 
    		"     C                   EXSR      FIN0\n" + 
    		"      * End\n" + 
    		"      *\n" + 
    		"     C                   SETON                                        LR\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     C/COPY QILEGEN,£INZSR\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"    RD* Initial subroutine (as *INZSR)\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     £INIZI        BEGSR\n" + 
    		"      *\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Initial settings\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     IMP0          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='IMP0 EXECUTED'                          COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Final settings\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     FIN0          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   EVAL      DSP='FIN0 EXECUTED'                          COSTANTE\n" + 
    		"     C                   DSPLY                   DSP\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*"),
    
    JAVA_CALL("     V*=====================================================================\n" + 
    		"     V* Date      Release Au Description\n" + 
    		"     V* dd/mm/yy  V5R1    AD Anonymous Developer\n" + 
    		"     V*=====================================================================\n" + 
    		"     H/COPY QILEGEN,£INIZH\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     I/COPY QILEGEN,£TABB£1DS\n" + 
    		"     I/COPY QILEGEN,£PDS\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"      * Invoke Url\n" + 
    		"      * . Function\n" + 
    		"     D §§FUNZ          S             10\n" + 
    		"      * . Method\n" + 
    		"     D §§METO          S             10\n" + 
    		"      * . Url\n" + 
    		"     D §§SVAR          S           1000\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     D DSP             S             50\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     D* M A I N\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"      * Initial settings\n" + 
    		"     C                   EXSR      IMP0\n" + 
    		"      * Call java program\n" + 
    		"     C                   EXSR      FESE\n" + 
    		"      * Final settings\n" + 
    		"     C                   EXSR      FIN0\n" + 
    		"      * End\n" + 
    		"     C                   SETON                                        LR\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"     C/COPY QILEGEN,£INZSR\n" + 
    		"      *---------------------------------------------------------------\n" + 
    		"    RD* Initial subroutine (as *INZSR)\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     £INIZI        BEGSR\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Initial settings\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     IMP0          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Final settings\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     FIN0          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Ese\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     FESE          BEGSR\n" + 
    		"      *\n" + 
    		"      * Invoke url\n" + 
    		"     C                   EVAL      §§FUNZ='URL'                                 COSTANTE \n" + 
    		"     C                   EVAL      §§METO='HTTP'                                COSTANTE\n" + 
    		"     C                   EVAL      §§SVAR='http://www.mocky.io/v2/'+            COSTANTE\n" + 
    		"     C                             '5185415ba171ea3a00704eed'                                    \n" + 
    		"      *\n" + 
    		"     C                   CALL      'JD_URL'\n" + 
    		"     C                   PARM                    §§FUNZ\n" + 
    		"     C                   PARM                    §§METO\n" + 
    		"     C                   PARM                    §§SVAR\n" + 
    	    "     C                   EVAL      DSP=§§SVAR\n" +
    	    "     C                   DSPLY                   DSP\n" +
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"    RD* Detach\n" + 
    		"      *--------------------------------------------------------------*\n" + 
    		"     C     FCLO          BEGSR\n" + 
    		"      *\n" + 
    		"     C                   ENDSR\n" + 
    		""),
    
    FIBONACCI("﻿      * Calculates number of Fibonacci in an iterative way\n" + 
			"     D ppdat           S              8\n" + 
			"     D NBR             S             21  0\n" + 
			"     D RESULT          S             21  0 INZ(0)\n" + 
			"     D COUNT           S             21  0\n" + 
			"     D A               S             21  0 INZ(0)\n" + 
			"     D B               S             21  0 INZ(1)\n" + 
			"     C     *entry        plist\n" + 
			"     C                   parm                    ppdat                          I\n" + 
			"      *\n" + 
			"     C                   Eval      NBR    = %Dec(ppdat : 8 : 0)\n" + 
			"     C                   EXSR      FIB\n" + 
			"     C                   clear                   dsp              50\n" + 
			"     C                   eval      dsp= 'FIBONACCI OF: ' +  ppdat +\n" + 
			"     C                                 ' IS: ' + %CHAR(RESULT)\n" + 
			"     C                   dsply                   dsp\n" + 
			"     C                   seton                                        lr\n" + 
			"      *--------------------------------------------------------------*\n" + 
			"     C     FIB           BEGSR\n" + 
			"     C                   SELECT\n" + 
			"     C                   WHEN      NBR = 0\n" + 
			"     C                   EVAL      RESULT = 0\n" + 
			"     C                   WHEN      NBR = 1\n" + 
			"     C                   EVAL      RESULT = 1\n" + 
			"     C                   OTHER\n" + 
			"     C                   FOR       COUNT = 2 TO NBR\n" + 
			"     C                   EVAL      RESULT = A + B\n" + 
			"     C                   EVAL      A = B\n" + 
			"     C                   EVAL      B = RESULT\n" + 
			"     C                   ENDFOR\n" + 
			"     C                   ENDSL\n" + 
			"     C                   ENDSR\n" + 
			"      *--------------------------------------------------------------*\n" + 
			"");
 
    private String source;
 
    HardcodedRPG(String source) {
        this.source = source;
    }
 
    public String getSource() {
        return this.source;
    }
	

}
