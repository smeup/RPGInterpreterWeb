     V*=====================================================================
     V* MODIFICHE Ril.  T Au Descrizione
     V* gg/mm/aa  nn.mm i xx Breve descrizione
     V*=====================================================================
     V* 12/03/20  001678  FP Creato
     V* 12/03/20  V5R1    BERNI Check-out 001678 in SMEDEV
     V*=====================================================================
     D FACTOR2         S              1  0
      *---------------------------------------------------------------
      * M A I N
      *---------------------------------------------------------------
     C                   SETOFF                                       34
     C                   Z-ADD     0             FACTOR2
     C   34              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(1) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETOFF                                       34
     C                   Z-ADD     0             FACTOR2
     C  N34              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(2) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETOFF                                       3435
     C                   Z-ADD     0             FACTOR2
     C   34
     COR 35              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(1) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETOFF                                       3435
     C                   Z-ADD     0             FACTOR2
     C   34
     CORN35              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(2) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETOFF                                       3435
     C                   Z-ADD     0             FACTOR2
     C   34
     CANN35              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(1) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETOFF                                       3435
     C                   Z-ADD     0             FACTOR2
     C   34
     CAN 35              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(1) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETOFF                                       3435
     C                   Z-ADD     0             FACTOR2
     C  N34
     CANN35              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(2) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETON                                        3435
     C                   Z-ADD     0             FACTOR2
     C  N34
     CANN35              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(1) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETOFF                                       343536
     C                   SETOFF                                       373839
     C                   Z-ADD     0             FACTOR2
     C   34
     CAN 35
     CANN36
     COR 37
     CAN 38
     CANN39              Z-ADD     1             FACTOR2
    MU* VAL1(FACTOR2) VAL2(1) COMP(EQ)
     C                   EVAL      FACTOR2+=1
      *
     C                   SETON                                        LR
      *---------------------------------------------------------------
