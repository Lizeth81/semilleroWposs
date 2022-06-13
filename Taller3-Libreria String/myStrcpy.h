char* myStrcpy (char *destino1, char *origen);

//3. copia una cadena en otra
char* myStrcpy (char *destino1, char *origen){
  while (*origen){
    *destino1 = *origen;
      origen++;
      destino1++;
    }
    *destino1= '\0';   

    return destino1;
}
