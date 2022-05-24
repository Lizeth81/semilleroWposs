int myStrncmp (char *origen, char *destino, int valor);

//7.Compara un numero de caracteres de ambas cadena
int myStrncmp (char *origen, char *destino, int valor){

  for(int i=0; i < valor; i++){
        if(destino[i] > origen[i]){
          return -1;     
        }else if(destino[i] < origen[i]){
          return 1;
        }
    return 0;
  }
}