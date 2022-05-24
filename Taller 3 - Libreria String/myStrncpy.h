char* myStrncpy (char *origen, char *destino2, int valor);

// 5. copia un numero de caracteres en otra cadena 
char* myStrncpy (char *origen, char *destino2, int valor){
  int i = 0;
  while(i<valor) {
    *destino2 = *origen;
    origen++;
    destino2++;
    i++;
 }
  *destino2 = '\0';
 
 return destino2;
}
