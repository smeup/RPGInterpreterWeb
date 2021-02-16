      * Esempio di moltiplicazione in rpgle free con concatenamento
      * della stringa 'Hello World', composta dalle due stringhe
      * in cirillico (Hello=Привет) e cinese (World=世界).
      *
     d  number1        s              7  0 
     d  number2        s              7  0 
     d  result         s              7  0 
     d  message        s             50  
      *                                               
      /free                                           
        number1 = 5;                                  
        number2 = 3;                                  
        result = number1 * number2;
        message = 'Привет 世界 ' + %char(result);          
        dsply message;                         
      /end-free                     
     c                   seton                                        lr                  