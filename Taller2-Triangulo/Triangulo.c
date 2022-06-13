#include<stdio.h>

int main(void){
    int n, i, j;
    
    printf("Ingrese un numero: ");
    scanf("%i", &n);

    for(i=n; i>=1; i--){
       for(j=n; j>= i; j--){
           printf("%i", j);
        }
        printf("\n");
    }   

    return 0;
}
