char* myStrcat(char *origen, char *destino);

//2. concatena dos cadenas.
char* myStrcat (char*origen, char*destino){
 while(*origen)
     origen++;
 while(*destino) {
         *origen = *destino;
         destino++;
         origen++;
     }
 *origen = '\0';

 return origen;
}