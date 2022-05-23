#include<stdio.h>

int main(void){
    char frase[30];

    printf("Ingrese una frase: ");

    scanf("%[^\n]s",frase);
    printf("%s\n", frase);

    int cantidad=strlen(frase); // cuenta numero de caracteres que se encuentran en el array

     for (int i = 0; i < cantidad; i++)
     {
        printf("%c", frase[i+1]);
        for (int j = 0; j < cantidad-2; j++)
        {
            printf(" ");
        }
        printf("%c", frase[cantidad-i-2]);
        if(frase[i+1] !='\0') {
            printf("\n");
        }else{
            printf("\r");
        }
     }

     for(int i=cantidad-1; i >= 0; i--){
         printf("%c", frase[i]);
     }
    return 0;

}