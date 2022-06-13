char* myStrncat (char *origen, char *destino, int valor);

// 4. concatena un numero de caracteres en otra cadena 
char* myStrncat (char *origen, char *destino, int valor){
  int i = 0;
  while (*origen){
    origen++;   
  }
  while(i<valor) {
    *origen = *destino;
    destino++;
    origen++;
    i++;
 }
  *origen = '\0';
 
 return destino;
}