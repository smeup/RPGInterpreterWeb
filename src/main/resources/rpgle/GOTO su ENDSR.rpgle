   COP* *NOUI
     V*=====================================================================
     V* MODIFICHE Ril.  T Au Descrizione
     V* gg/mm/aa  nn.mm i xx Breve descrizione
     V*=====================================================================
     V* 12/03/20  001676  LOMFRA Created
     V* 12/03/20  V5R1    BERNI Check-out 001676 in SMEDEV
     V*=====================================================================
     V* OBIETTIVO
     V*  Test program for GOTO to ENDSR tag
     V*
     V*=====================================================================
     D*----------------------------------------------------------------
     C                   EXSR      SR1
    MU* VAL1(N) VAL2(3) COMP(EQ)
     C     'End'         DSPLY
     **********************************************************************
     C                   SETON                                          LR
     **********************************************************************
     C     SR1           BEGSR
     C                   CLEAR                   N                 1 0
     C     START         TAG
     C                   IF        N >= 3
     C                   GOTO      endsr1
     C                   ENDIF
     C                   ADD       1             N
     C     N             DSPLY
     C                   goto      start
     C     ENDSR1        ENDSR

