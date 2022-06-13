int myStrlen(char *origen);

//1. longitud de una cadena.
int myStrlen(char *origen) {
  if (*origen == '\0') 
    return 0;

  return (1 + myStrlen(++origen));
}