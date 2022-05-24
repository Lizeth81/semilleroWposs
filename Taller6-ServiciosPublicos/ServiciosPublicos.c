#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <conio.h>
#include <time.h>
#include <ctype.h>

#define ENTER 13
#define RETROCESO 8

/*Metodos*/
void Menu();
void Opciones(char opc);
void IniciarSesion(int n);
void Registro(int n);
int CantidadUsuario();
void IngresoSistema(char *usuario, int n, int consignacion);
int validarUsuarioLogin(int n, char *usuario, char *contrasena);
int validarUsuarioRegistro(int n, char *usuario, char *contrasena);
char Contrasena(char *contrasena);
int ValidarCantidadNumero(char *usuario);
int validarNumeros(char *usuario);
int CamposVacios(char *nombre, char *contrasena);
int fechaActual();
void MenuTipoFactura(char opcion);
void AceptarFactura(char *usuario, char *factura, int monto, int numSaldo, int consignacion);
void Factura(char *usuario, int n, int consignacion);

/*Estructuras*/
typedef struct datos_usuario{
    char cedula[10+1];
    char password[10];
    int monto;
}Usuario;

/*Variables globales*/
int montoUsuario;
int rep =0;
char TipoFactura[15];

Usuario user[10]={{"10", "1234", 4000},
                  {"1090521932", "1234", 4000},
                  {"1090456667", "1234", 5000}
                 };
int main(){
    Menu();
	return 0;
}
//Funcion de menu
void Menu(){
	char opc;	
	do{
		system("cls");
	    printf("\t\tBIENVENIDOS\n");
	     printf("\t\t===========\n");
	    printf("\n");
	    printf("1.  Iniciar sesion\n");
	    printf("2.  Registrar usuario nuevo\n");
	    printf("0.  Salir");
	    printf("\n");
	    printf("Ingrese una opcion: ");
	    scanf ("%c", &opc);
	    
	    if(opc =='1' || opc =='2' || opc == '0'){
	    	 Opciones(opc);
	    	 break;
		}else{
			rep=1;
		}  
	   
	}while(rep==1);
}
//Funcion de opciones
void Opciones(char opc){
	int n;
	 switch (opc) {
                case '1':
                        CantidadUsuario();
				        n=CantidadUsuario();
                        IniciarSesion(n);
                        break;

                case '2': 
                        CantidadUsuario();
				        n=CantidadUsuario();
                        Registro(n);
                        break;
                
                case '0':
                        rep = 0;
                        break;
                        
            }
}
//Funcion de inisiar sesion
void IniciarSesion(int n){
int intentos = 0;
int ingreso = 0; 
char usuario[10+1];
char contrasena [10];


    do{      
       system("cls");
        printf("\n\t\t\tINICIAR SESION\n");

        printf("\n\t\tUsuario: ");
        fflush(stdin);
        gets(usuario);
        printf("\n\t\tContrasena: ");
        Contrasena(contrasena); 

      //  Validar si el usuario ya esta registrado en  el sistema
        int validar = validarUsuarioLogin(n, usuario, contrasena);
       // printf("%d", montoUsuario);
         if(validar==0){            
               ingreso = 1;
         }else{
            printf("\n");
            printf("\n\t--> Usuario y/o contrasena son incorrectas");
            intentos ++;
            getchar();
         }
    }while(intentos<3 && ingreso==0);
    // Direcciona al sistema o al menu 
    if (ingreso == 1){
        int consignacion = montoUsuario;
        IngresoSistema(usuario, n, consignacion);
    }else{
        Menu();
    }
}
//Funcion de registro
void Registro(int n){
int ingreso = 0;
char usuario[10+1];
char contrasena[10];
int nuevoNumeroUsuario = 0;
int mont;
do{
    system("cls");
    printf("\n\t\tREGISTRAR USUARIO\n");
    printf("\n");
    printf("\n\tNumero de Cedula: ");
    fflush(stdin);
    gets(usuario); 

    int validar = validarUsuarioRegistro(n, usuario, contrasena);
    int validar2 = ValidarCantidadNumero(usuario);
    int validar3 = validarNumeros(usuario);
   // int validar4 = CamposVacios(nombre, contrasena);
        
         if(validar==0 && validar2==0 && validar3==1) {               
            strcpy(user[n].cedula, usuario);
            printf("\n\tContrasena: ");
            Contrasena(contrasena);            
            strcpy(user[n].password, contrasena);

            regresar:
            printf("\n ");
            printf("\n\tPara completar el registro por favor digite el monto a consignar: ");
            scanf("%d", &mont);
            if(mont >= 1000){ 
                user[n].monto = mont; 
                }else{
                    printf("\n "); 
                    printf("\n\t--> Monto incorrecto, por favor ingrese un monto minimo de 1000 pesos");
                    goto regresar;   
            }
            ingreso = 1;
         }else {
             printf("\n\t -->Cedula incorrecta!!!");
             getchar();
         }

}while(ingreso == 0);

if (ingreso == 1){
        nuevoNumeroUsuario = CantidadUsuario();
        IniciarSesion(nuevoNumeroUsuario);
    }else{
      Menu();
    }             
}
//Funcion para calcular la cantidad de usuarios en
int CantidadUsuario(){
    int n, e=0;
    int i = 0;
    n = sizeof(user)/sizeof(user[0]);
    for(i; i < n; i++){
        if(strcmp(user[i].cedula,"") != 0){
            e++;
        }
    }
    return e;  
}
//Funcion Validar login       
int validarUsuarioLogin(int n, char *usuario, char *contrasena){
    int validar = 1;
    int j=0;
     for(j; j < n; j++){    
           if(strcmp(usuario, user[j].cedula) == 0 && strcmp(contrasena, user[j].password) == 0){   
                montoUsuario = user[j].monto;
                validar = 0;
                break;
            }
       }

       return validar;
}
//Funcion de validar registros
int validarUsuarioRegistro(int n, char *usuario, char *contrasena){
    int validar = 0;
    int j=0;
     for(j; j < n; j++){          
         if(strcmp(usuario, user[j].cedula) == 0){
             validar = 1;
             printf("\n");
             printf("\n\t--> Usuario ya registrado");
             getchar();
             break;
         }
       }

       return validar;
}
//Funcion de contraseña de la
char Contrasena(char *contrasena){
char caracter;
int i=0;

  while(caracter = getch()){
                if(caracter == ENTER){
                    contrasena[i] = '\0';
                    break;
                }else if(caracter == RETROCESO){
                    if(i>0){
                    i--;
                    printf("\b \b");
                    }
                }else{
                    if(i<8){
                    printf("*");
                    contrasena[i] = caracter;
                    i++;
                    }
                }
        }
}
//Funcion de validar cedula
int ValidarCantidadNumero(char *usuario){
    int validarUsua=0;
    if(strlen(usuario) != 10){
             validarUsua = 1;
             printf("\n");
        }
return validarUsua;
} 
//Funcion de calidar solo numeros
int validarNumeros(char *usuario){
	int i=0;
 for (i; i < strlen(usuario); i++){
     if (!(isdigit(usuario[i]))){
         return 0;
     }
 }
 return 1;
}
//Funcion de la fecha
int fechaActual(){
    time_t t = time(NULL);
    struct tm tiempoLocal = *localtime(&t);
    char fechaHora[70];
    char *formato = "%Y-%m-%d %H:%M:%S";
    int bytesEscritos = strftime(fechaHora, sizeof fechaHora, formato, &tiempoLocal);
    if (bytesEscritos != 0) {
        printf("\n");
        printf("\t %s\n", fechaHora);
    } else {
        printf("Error formateando fecha");
   }
}
//Funcion ingreso al sistema
void IngresoSistema(char *usuario, int n, int consignacion){  
     char opcion;
     int nuevoNumeroUsuario; 
     int numMonto;
     int total; 
     int intento;
     int ingreso = 0;
     do{
        system("cls");
        printf("\t\t BIENVENIDOS AL PAGO DE FACTURA DE SERVICIOS PUBLICOS \n");
        fechaActual();
        printf("\t Usuario: %s\n", usuario);
        printf("\t Total monto consignado: %d\n", consignacion);
        printf("\n");
        printf(" MENU PRINCIPAL \n");
        printf("\n");
        printf("\b [1]. Pagar factura \n [2]. Realizar deposito \n [3]. Cerrar sesion \n ");        
        printf("Ingrese una opcion: ");
        scanf ("%c", &opcion);

        if(opcion == '1'){
            Factura(usuario, n, consignacion);
            break;
        }else if( opcion == '2'){      
            break;
        }else if(opcion == '3'){
            Menu();
        }else{
            rep = 1;
        }
    }while(rep == 1);  

    do {
        printf("\n");
        printf("Ingres el valor del monto a consignar: ");
        scanf ("%i", &numMonto);

        if (numMonto >= 1000){
            total = numMonto + consignacion;
            user[n].monto = total;
            printf("nuevo monto %i", user[n].monto);
            getchar();
            IngresoSistema(usuario, n, total);
        }else{
            printf("\n\t -->Monto incorrecto!!!");
            getchar();
            rep = 1;
        }
    }while (rep ==1);
    
}
//Funcion de facturacion
void Factura(char *usuario, int n, int consignacion){
    char opcion;
    char factura[10+1];
    int monto;
    int ingreso;
    do{
        system("cls");
        printf("\n");
        printf("\t\tPAGO DE FACTURA DE SERVICIOS PUBLICOS \n");
      
        printf(" Seleccionar el tipo de factura \n");
        printf("\b [1]. CENS \n [2]. AGUAS KAPITAL \n [3]. VEOLIA \n [4]. DIRECTV \n [5]. CANAL EXITO \n");        
        printf(" Ingrese una opcion: ");
        scanf ("%c", &opcion);

        if(opcion == '1' || opcion == '2' || opcion == '3' || opcion == '4' || opcion == '5'){
            MenuTipoFactura(opcion);
            break;
        }else{
            rep = 1;
        }
    }while(rep == 1);

    do{
        printf("\n Ingresar el Numero de la factura: ");
        scanf("%s", &factura);
        int validarLong = ValidarCantidadNumero(factura); 
        int validarNum = validarNumeros(factura);

        if(validarLong == 0 && validarNum == 1){             
            break;          
        }else{
            printf("\t--> Numero Incorecto!!! La Factura debe de tener mas de 10 caracteres");
            rep=1;
        }  
    }while(rep ==1);

    do{
        printf("\n Ingrese el monto a pagar: ");
        scanf ("%i", &monto);

        if(monto >= 1000){
            AceptarFactura(usuario, factura, monto, n, consignacion);            
        }else{
            printf("\t--> Monto incorrecto!!! El monto a consignar debe der mayor a 1000");
            getchar();
            rep=1;
        }     
    }while(rep ==1);   

}
//Sub menu de tipo factura
void MenuTipoFactura(char opcion){  

     switch (opcion){
        case '1':
        strcpy(TipoFactura, "CENS");
        break;

        case '2':
        strcpy(TipoFactura, "AGUAS KAPITAL");
        break;

        case '3':
        strcpy(TipoFactura, "VEOLIA");
        break;

        case '4':
        strcpy(TipoFactura, "DIRECTV");
        break;

        case '5':
        strcpy(TipoFactura, "CANAL EXITO");
        break;        
    }  
}
// Funcion de Aceptar su facturacion
void AceptarFactura(char *usuario, char *factura, int monto, int n, int consignacion){    

    char opcion;
    int ingreso;    
    int NuevoMonto;

   do{
        system("cls");
        printf(" \n");
        fechaActual();
        printf("\tUsuario: %s\n", usuario);
        printf("\tTotal monto consignado: %d\n", consignacion);
        printf("\n\t---------------------------------------------\n");
        printf("\tTipo de Factura: %s\n", TipoFactura);
        printf("\tNumero de la factura: %s\n", factura);
        printf("\tMonto a pagar: %i\n", monto);
         printf("\n\t----------------------------------------------");
         printf(" \n\t[1]. Si los datos son corretos \n\t[2]. Si los datos son incorrectos");
         printf("\n\t Digite una opcion: ");
         scanf("%c", &opcion);

        if (opcion == '1'){            
             if(monto >= consignacion){
                fflush(stdin);
                 printf("\n\t----------------------------------------------");
                printf("\n");
                printf("\t Saldo insufuciente!!! \n\tSu pago sera cancelado por no tener saldo suficientes.\n\tENTER para regresar al inicio y pueda realizar una\n\tnueva consignacion");
                getchar();
                ingreso = 1; 
				break;      
             }else{
                fflush(stdin);
                printf("\n\t----------------------------------------------");
                NuevoMonto = (consignacion - monto);
                user[n].monto = NuevoMonto;
                printf("\n\tSu nuevo saldo es: %i!", NuevoMonto);
                getchar();
                ingreso = 0;
                break;
             }           
        }else if(opcion == '2'){
            fflush(stdin);
            printf("\n\t------------------------------------------");
            printf("\n\tENTER para regresar al inicio...");
            getchar();
            ingreso = 1; 
           break;           
        }else {
            rep = 1;
        }          
   }while(rep ==1);

   if (ingreso == 0){
       IngresoSistema(usuario, n, NuevoMonto);
   }else{
       IngresoSistema(usuario, n, consignacion);
   }
}
