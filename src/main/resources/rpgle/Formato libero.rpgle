      * Esempio di moltiplicazione in rpgle free e gestione
      * di una stringa con caratteri Russi e Cinesi
      *
     D  number1        S              7  0 
     D  number2        S              7  0 
     D  result         S              7  0 
     D  message        S             50  
      *                                               
      /FREE                                           
        number1 = 5;                                  
        number2 = 3;                                  
        result = number1 * number2;
        message = 'результат 结果 ' + %char(result);          
        dsply message;                         
      /END-FREE                     
     C                   seton                                        lr                  