#include<stdio.h>
#include "myStrlen.h"
#include "myStrcat.h"
#include "myStrcpy.h"
#include "myStrncat.h"
#include "myStrncpy.h"
#include "myStrcmp.h"
#include "myStrncmp.h"

char origen[100], destino[100];
int valor=3;
int resultado;


int main(){

 //-- 1. Prueba de longitud de una cadena
    system("cls");
    printf("\t----------------------------------------------------------\n");
    printf("\t\t1. Prueba de longitud de una cadena --myStrlen--\n");
    printf("Ingrese una palabra: " );
    gets(origen);
    printf("numero de caracteres es: %i\n", myStrlen(origen)); 
    getchar();
 

  //-- 2. Prueba de concatenar dos cadenas
   system("cls");
   printf("\t----------------------------------------------------------\n");
   printf("\t\t2. Prueba de concatenar dos cadenas --myStrcat--\n");
   printf("Ingrese una frase: ");
   gets(origen);
   printf("Ingrese una segunda frase: ");
   gets(destino);
   myStrcat(origen, destino);
   printf("Su frase completa es:  %s\n", origen);
   getchar();
 
 //-- 3. Prueba de copia una cadena en otra 
   system("cls");
   printf("\t----------------------------------------------------------\n");
   printf("\t\t3. Prueba de copia una cadena en otra --myStrcpy--\n");
   char destino1[100];
   printf("Digite 1 palabra: ");
   gets(origen);
   myStrcpy(destino1, origen);
   printf("su palabra copiada es: %s\n", destino1);
   getchar();
   
 //-- 4. Prueba de concatenar un numero de caracteres en otra cadena
    system("cls");
    printf("\t----------------------------------------------------------\n");
    printf("\t\t4. Prueba de concatenar un numero de caracteres en otra cadena --myStrncat--\n");
    printf("Primera cadena: ");
    gets(origen);
    printf("Segunda cadena: ");
    gets(destino);
    myStrncat(origen, destino, valor);
    printf("%s\n%s\n", origen, destino);
    getchar();

//-- 5. Prueba de copia un numero de caracteres en otra cadena
    system("cls");
    printf("\t----------------------------------------------------------\n"); 
    printf("\t\t5. Prueba de copia un numero de caracteres en otra cadena --myStrncpy--\n");
    char destino2[100];
    printf("Digite una frase: ");
    gets(origen);
    myStrncpy(origen, destino2, valor);
    printf("%s\n%s\n", origen, destino2);
    getchar();

//-- 6. Prueba de comparando dos cadenas
    system("cls");  
    printf("\t----------------------------------------------------------\n"); 
    printf("\t\t6. Prueba de comparando dos cadenas --myStrcmp--\n");
    printf("Primera palabra:");
    gets(origen);
    printf("Segunda palabra:");
    gets(destino);

    myStrcmp(origen, destino);
    resultado = myStrcmp(origen, destino);
    printf("%i\n", resultado);
    
    if(resultado == 0){
      printf("Las palabras son iguales ");
    }else if(resultado == -1){
      printf("La Cadena 1 es menor que la cadena 2");
    }else {
     printf("la cadena 1 es mayor que la cadena 2");
    }

    getchar();
     
//-- 7. Prueba de compara un numero de caracteres de ambas cadena
    system("cls");
    printf("\t----------------------------------------------------------\n"); 
    printf("\t\t7. Prueba de comparar un numero de caracteres de ambas cadena --myStrncmp--\n");
    printf("Primera palabra:");
    gets(origen);
    printf("Segunda palabra:");
    gets(destino);
    myStrncmp(origen, destino, valor);

    resultado = myStrncmp(origen, destino, valor);
     printf("%i\n", resultado);

    if(resultado == 0){
       printf("Las palabras son exactamente iguales");
    }else if(resultado == 1){
      printf("Las primera palabra es mayor que la segunda palabra");
    }else{
     printf("Las primera palabra es menor que la segunda palabra");
    }

    getchar();

    return 0;
}
