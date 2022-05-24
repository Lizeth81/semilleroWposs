#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <windows.h>

void Vista(int x, int y , int num_bin[]);
void EstructuraGotoxy(int x, int y);
int Calcular(int valor, int recorrido, int tope, int num_bin[]);

int num_bin[64];


int main(){ 
   char num_hexa[17];
   int valor;
   int longitud, i=0, tope=0, recorrido=3, x=4, y=2, k=0;
   int m=0; 

   do{
      system("cls");
      printf("\t\t BIENVENIDOS \n");
      printf("\t\t =========== \n");
      printf("\t Ingresar un numero hexadecimal:");
      gets(num_hexa);
      system("cls");
      longitud = strlen(num_hexa);
      //Convertir la cadena en mayusculas
      for (int indice = 0; num_hexa[indice] != '\0'; ++indice){
		num_hexa[indice] = toupper(num_hexa[indice]);
	   }
      
     while(i<longitud){

        switch(num_hexa[i]){
           case 'A':
            valor=10;
            Calcular(valor, recorrido, tope, num_bin);
            tope=tope+4;
            recorrido=recorrido+4;
            m=0;
            break;
            
            case 'B':
            valor=11;
            Calcular(valor, recorrido, tope, num_bin);
            tope=tope+4;
            recorrido=recorrido+4;
            m=0;
            break;

            case 'C':
            valor=12;
            Calcular(valor, recorrido, tope, num_bin);
            tope=tope+4;
            recorrido=recorrido+4;
            m=0;
            break;

            case 'D':
            valor=13;
            Calcular(valor, recorrido, tope, num_bin);
            tope=tope+4;
            recorrido=recorrido+4;
            m=0;
            break;

            case 'E':
            valor=14;
            Calcular(valor, recorrido, tope, num_bin);
            tope=tope+4;
            recorrido=recorrido+4;
            m=0;
            break;

            case 'F':
            valor=15;
            Calcular(valor, recorrido, tope, num_bin);
            tope=tope+4;
            recorrido=recorrido+4;
            m=0;
            break;

            default:

             if(num_hexa[i]>='0' && num_hexa[i]<='9'){
                  valor=num_hexa[i]-'0';
                  Calcular(valor, recorrido, tope, num_bin);
                  tope=tope+4;
                  recorrido=recorrido+4;
                  m=0;
               }
               else {
                  i=longitud;
                  m= 1;
                  y=y+4;            
               }
                break;
        }
        i++;     
       } 
              
   }while (m==1); 
   Vista(x,y,num_bin);
   printf("\n\n\tnumero hexadecimal: %s", num_hexa);
    return 0;
} 
int Calcular(int valor, int recorrido, int tope, int num_bin[]){
	
	int i=recorrido;
	while(i>=tope){	
		num_bin[i]=valor%	2;
		valor = valor/2;  
		i--;
	}
	return *num_bin;
}

void EstructuraGotoxy (int x, int y) {
	HANDLE hcon;
	COORD dwPos;
	hcon = GetStdHandle(STD_OUTPUT_HANDLE);
	dwPos.X = x;
	dwPos.Y = y;
	SetConsoleCursorPosition(hcon,dwPos);
}
void Vista(int x, int y , int num_bin[]){
	
	int x1=0, y1=y;
	int li=y+16;
	
	while(x1<64){
		if(num_bin[x1]==0){
			EstructuraGotoxy(x,y);
			printf("\t  bit 0%d ",x1+1);
			y++;
		}else{
			EstructuraGotoxy(x,y);
			printf("\t%c bit 0%d ", 16,x1+1); 
			y++;
		}
		if(y==li){
			x=x+25;
			y=y1;			
		}
		x1++;
	}

   
}