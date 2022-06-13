#include <stdio.h>
#include <time.h>
#include <string.h>
#include <conio.h>

/*Estructura*/
typedef struct parqueaderoWposs
{
    char placa[10];
    int horaEntrada;
    int horaSalida;

}parqueadero;
parqueadero moto[6],carro[10];

/*Funciones*/
void inicio();
void ingresarVehiculo();
void fechaHora();
int hxm();
void retirarVehiculo();
void facturaCarro(int n);
void facturaMoto(int n);
void carroMenu();
void motoMenu();


int main(){
    inicio();
    return 0;
}
void inicio(){
    char opcion;
    int rep;
    do
    {
        system("cls");
	    printf("\tBIENVENIDOS AL SISTEMA DE PARQUEADERO\n");
	     printf("\t=====================================\n");
	    printf("\n");
        printf("\n [1] Ingresar vehiculo\n [2] Retirar vehiculo \n [0] Salir");
        printf("\n");
        printf("\nSeleccione una opcion:");
        scanf("%c",&opcion);

        if (opcion=='1'){
            ingresarVehiculo();
        }else if (opcion=='2'){
             retirarVehiculo();
        }else if (opcion=='0'){
             rep = 0;
        }else{
            rep=1;
        }
    }while (rep==1);
}
void ingresarVehiculo(){
    int rep;
    char opc;
    do{
        system("cls");
        printf("\n\tNuevo vehiculo");
        printf("\n\t==============\n");
        printf("\n[1] Vehiculo automotor\n[2] Vehiculo motorizado");
        printf("\nSeleccione una opcion: ");
        fflush(stdin);
        scanf("%c",&opc);

        if (opc=='1'){
            carroMenu();
        }
        else if (opc=='2'){
            motoMenu();
        }
        else{
            rep=1;
        }
    } while (rep==1);
}
void fechaHora(){
  time_t t = time(NULL);
  struct tm tiempoLocal = *localtime(&t);
  char fechaHora[70];
  char *formato = "%Y-%m-%d %H:%M";
  int bytesEscritos =strftime(fechaHora, sizeof fechaHora, formato, &tiempoLocal);
  if (bytesEscritos != 0) {
    printf("fecha y Hora: %s\n", fechaHora);
  } else {
    printf("Error formateando fecha");
  }
}
int hxm(){
    int h,m,k;
    time_t t;
    struct tm *st ;
    time(&t);
    st = localtime(&t);
    h=st-> tm_hour*60;
    m=st-> tm_min;
    k=h+m;
    return k;
}
void retirarVehiculo(){
    int rep, n;
    char opc;
    do{
        system("cls");
        printf("\n\tRetiro de vehiculo");
        printf("\n\t==============");
        printf("\n[1] Vehiculo automotor\n[2] Vehiculo motorizado");
        printf("\nIngrece una opcion:");

        scanf("%c",&opc);
        if (opc=='1'){
            for (int i = 0; i < 10; i++){
                if(strcmp(carro[i].placa,"")!=0){
                    printf("\nPuesto %i ocupado a facturar",i+1);
                }
            }

            printf("\nEliga el puesto de estacionamiento ");
            scanf("%i",&n);
            hxm();
            int k=hxm();
            carro[n-1].horaSalida=k;
            facturaCarro(n);
        }
        else if (opc=='2')
        {
            int n;
            for (int i = 0; i < 6; i++){
                if(strcmp(moto[i].placa,"")!=0){
                    printf("\nPuesto %i ocupado a facturar",i+1);
                }
            }
            printf("\nEliga el puesto de estacionamiento ");
            scanf("%i",&n);
            hxm();
            int k=hxm();
            moto[n-1].horaSalida=k;
            facturaMoto(n);
        }
        else{
            rep=1;
        }
    } while (rep==1);
}
void facturaCarro(int n){
    int r,h,m,h1,m1,t;
    r=carro[n-1].horaSalida-carro[n-1].horaEntrada;
    h=r/60;
    m=r%60;
    if (m>2){
       h1=h*3000;
       m1=m*200;
       t=h1+m1;
    }else{
        t=h*3000;
    }

    printf("\nEl saldo a pagar para el carro de placas %s es de :%i",carro[n-1].placa,t);
    getchar();
    memset(&carro[n-1],0,sizeof(&carro[n-1]));
    getchar();
    inicio();
}
void facturaMoto(int n){
    int r,h,m,h1,m1,t;
    r=moto[n-1].horaSalida-moto[n-1].horaEntrada;
    h=r/60;
    m=r%60;
    if (m>2){
       h1=h*1000;
       m1=m*200;
       t=h1+m1;
    }else{
        t=h*1000;
    }

    printf("\nEl saldo a pagar para el carro de placas %s es de :%i",moto[n-1].placa,t);
    memset(&moto[n-1],0,sizeof(&moto[n-1]));
    getchar();
    inicio();
}
void carroMenu(){
    int n;
    char v[20];
    fflush(stdin);
    for (int i = 0; i < 10; i++){
        if(strcmp(carro[i].placa,"")==0){
            printf("\nPuesto %i Disponible",i+1);
        }        
    }
    printf("\nEliga el puesto de estacionamiento disponible: ");
    scanf("%i",&n);
    fflush(stdin);
    printf("\nIngrese la placa del vehiculo: ");
    scanf("%s",&v);
    int r;
    for (int i = 0; i < n; i++){
        r=strcmp(carro[i].placa,v);
        if (r==0){
            break;
        }       
    }
    if (r==0){
        printf("Placa ya registrada, intente de nuevo");
        inicio();
    }else{
        strcpy(carro[n-1].placa,v);
    }
    fechaHora();
    hxm();
    int m=hxm();
    carro[n-1].horaEntrada=m;
    getchar();
    inicio();
}
void motoMenu(){
    int n;
    char v[20];
    fflush(stdin);
    for (int i = 0; i < 6; i++){
        if(strcmp(moto[i].placa,"")==0){
            printf("\nPuesto %i Disponible",i+1);
        }        
    }
    printf("\nEliga el puesto de estacionamiento ");
    scanf("%i",&n);
    printf("\nIngrese la placa del vehiculo: "); 
    scanf("%s",&v);
    int r;
    for (int i = 0; i < 6; i++){
        r=strcmp(moto[i].placa,v);
        if (r==0){
            break;
        }       
    }
    if (r==0){
        printf("Placa ya registrada, intente de nuevo");
        inicio();
    }else{
        strcpy(moto[n-1].placa,v);
    }

    fechaHora();
    hxm();
    int m=hxm();
    moto[n-1].horaEntrada=m;
    getchar();
    inicio();
}