int myStrcmp (char *origen, char *destino);

//6. comparando dos cadenas
int myStrcmp (char *origen, char *destino){
    while (*origen == *destino){
        if(*origen == '\0' || *destino == '\0'){
            return 0; 
            break;
        }
        origen++;
        destino++;    
    }
    // if (*origen == '/0' && *destino == '\0'){
    if (*origen < *destino ){
        return -1;
    }else{
        return 1;  
    }
    
     
}