#include <stdio.h>
#include <string.h>

#define ENTER 13
#define RETROCESO 8

/*Estructura usuario*/
typedef struct usuario{
     char persona[11];
     char pass[5];
}Usuario;
Usuario user[10]={{"88229967", "2002"}};

/*Estructura de los platos y sus respectivos precios*/
typedef struct platosPrecio{
    char nombre[35];
    int precio;
}Plato;
Plato platoEntrada[4] = {{"Guacamole", 6000},
                         {"Choriqueso", 7000},
                         {"Ensada de pollo", 7000},
                         {"Patacon con hogao", 7000}};

Plato platoFuerte[4] = {{"Pasta napolitana", 16000},
                        {"Currasco", 20000},
                        {"Costillas de cerdo", 20000},
                        {"Creps de pregano", 15000}};

Plato postre[4] = {{"Postre de maracuya", 6000},
                   {"Postre de limon", 6000},
                   {"Postre frutos rojos", 6000},
                   {"Torat de queso", 6000}};

Plato bebida[4] ={{"Limonada frutos del Bosque", 7000},
                  {"Frape de maracuya", 5000},
                  {"Te helado de limon", 7000},
                  {"Gaseosa personal", 2500}};

typedef struct pedidoCliente{
    char cliente[20];
    char mesa[2];
    struct platosPrecio platoEntrada[30];
    struct platosPrecio platoFuerte[30];
    struct platosPrecio postre[30];
    struct platosPrecio bebida[30];
    int quantity; //Cantidad
    int total_sale; //total de la venta
}Pedido;
Pedido pedido[20];

/*Funciones*/
//funciones de inicio de sesion
void iniciarSesion();
int caracteresUsuario(char *usuario);
char contrasena(char *contrasena);
int caracteresPass(char *contrasena);
int validarUsuarioLogin(char *usuario, char *contrasena);
int cantidadUsuario();
//Funciones de menu principal
void menuPrincipal(int n);
void ingresoCliente(int n);
void tomarPedido();
void pedidoMesa(int mesa, int n);
void platosEntradas(int mesa, int n);
void platosFuerte(int mesa, int n);
void platosPostres(int mesa, int n);
void platosBebidas(int mesa, int n);
void factura(int n);





int main(){
    iniciarSesion();
	return 0;
}
//Funcion de inisiar sesion
void iniciarSesion(){
int intentos1 = 0;
int intentos2 = 0;
int ingreso1 = 0; 
int ingreso2 = 0; 
char usuario[11];
char pass [5];

system("cls");
printf("\n\t\t\tINICIAR SESION\n");

    do{      
        printf("\n\t\tUsuario: ");
        fflush(stdin);
        gets(usuario);

      int validarUser = caracteresUsuario(usuario);
       if(validarUser == 0){
             ingreso1 = 1;
        }else{
            printf("\tUsuario incorrecto\n");
            intentos1 ++;
            getchar();
        }
    }while(intentos1<3 && ingreso1 == 0);

    do{
         printf("\n\t\tContrase%ca: ", 164);
         fflush(stdin);
         contrasena(pass); 
        int validarPass = caracteresPass(pass);
        if(validarPass == 0){
            ingreso2 = 1;
            break;            
        }else{
            printf("\tContrase%ca incorrecta", 164);
            intentos2 ++;
            getchar();
        }
    }while(intentos2<3 && ingreso2 == 0);
    
    int validar = validarUsuarioLogin(usuario, pass);
  
        if(validar==0){
            int n = cantidadUsuario();
           menuPrincipal(n);
        }else{
            printf("\n Usuario y/o contrase%ca son incorrectas", 164);
            getchar();
            iniciarSesion();
        }
}
// Funcion para validar numero caracteres del usuario
int caracteresUsuario(char *usuario){
    int n = strlen(usuario);
   if(n > 3 && n < 10){
           return 0;
       }else {
           return 1;
       }
}
//Funcion de contraseña de la
char contrasena(char *contrasena){
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
// Funcion para validar numero caracteres de la contraseña
int caracteresPass(char *contrasena){
    if(strlen(contrasena) == 4){
           return 0;
       }else {
           return 1;
       }
}
//Funcion Validar login       
int validarUsuarioLogin(char *usuario, char *contrasena){
    if(strcmp(usuario, user[0].persona) == 0 && strcmp(contrasena, user[0].pass) == 0){   
        return 0;
    }
       return 1;
}
//Funcion para calcular la cantidad de usuarios registrados
int cantidadUsuario(){
    int n=0, e=0;
    int i;
    n = sizeof(pedido)/sizeof(pedido[0]);
    for(i = 0; i < n; i++){
        if(strcmp(pedido[i].mesa,"") != 0){
            e++;
        }
    }
    return e;  
}


/*Funciones del menu pricipal*/
// Funcion menu principal
void menuPrincipal(int n){
     char opcion;
     int rep;
    do{
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("\n");
          printf("\tMenu principal \n");
        printf("\b [1]. Ingresar ciente \n [2]. Tomar pedido \n [3]. Imprimir factura de mesa \n [4]. Cerrar sesion \n");        
        printf("Ingrese una opcion: ");
        scanf ("%c", &opcion);

        if(opcion == '1'){
           ingresoCliente(n);
            break;
        }else if( opcion == '2'){ 
            tomarPedido();
            break;
        }else if(opcion == '3'){
            factura(n);
            break;
        }else if(opcion == '4'){
             main();
            break;
        }else{
            rep = 1;
        }
    }while(rep == 1);  
}
//Ingresar el cliente y la mesa
void ingresoCliente(int n){
    int i;
    char nombre[35];
    char mesa[2];
    int rep;
    do{
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("\n");
        printf("\tIngresar cliente\n");
         printf("numero clientes en la estructura: %i\n", n);
        printf("Ingresar el nombre del cliente: ");
        scanf("%s", &nombre);
        printf("Ingresar numero de la mesa: ");
        scanf("%s", &mesa);      

        for ( i = 0; i <= n; i++){
            //Compara las cadenas
            if(strcmp(mesa, pedido[i].mesa)==0){
                printf("\tMesa ya reservada\n");
                getchar();
                rep = 0;
            }else{
                //le asigna el numero de mesa. Copiar una cadena en otra
                strcpy(pedido[i].mesa, mesa);
                strcpy(pedido[i].cliente, nombre);
                printf("\tSe a agregado el cleinte con exito!\n");
                getchar();
                tomarPedido(n);
                rep = 1;
            }
        }

    }while(rep==0);        
 }
 //Funcion de ingreso al sistema
void tomarPedido(int n){
  char mesa[2];
  int i;
  int rep;
    do{
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("\n Numero de la mesa: ");
        scanf ("%s", &mesa);

        for ( i = 0; i <= n; i++){
        //Compara las cadenas
        if(strcmp(mesa, pedido[i].mesa)==0){
            rep = 0;
            break;
        }else{
           printf("\tMesa no registrada\n");
           getchar();
           rep = 1;
           break;
        }
    }    
    }while(rep == 1);  

    if(rep == 0){
        pedidoMesa(i, n);
    }
}
//Funcion del pedido
void pedidoMesa(int mesa, int n){
  char opcion;
  int rep;
     do{
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("Realizar pedido\n");
        printf("\t Cliente: %s\n",  pedido[mesa].cliente);
        printf("\t Mesa: %s\n", pedido[mesa].mesa);
        printf("\n");
        printf(" Tomar pedido del cliente: \n");
        printf("\n");
        printf("\b [1]. Plato de entrada \n [2]. Plato fuerte \n [3]. Postre \n [4]. Bebidas \n [5]. Generar Factura \n [6]. Inicio \n ");        
        printf("Ingrese una opcion: ");
        scanf ("%c", &opcion);

        if(opcion == '1'){
            platosEntradas(mesa, n);
            break;
        }else if( opcion == '2'){ 
            platosFuerte(mesa, n);     
            break;
        }else if(opcion == '3'){
             platosPostres(mesa, n); 
            break;
        }else if(opcion == '4'){
            platosBebidas(mesa, n); 
            break;
        }else if(opcion == '5'){
            break;
        }else if(opcion == '6'){
            menuPrincipal(n);
            break;
        }else{
            rep = 1;
        }
    }while(rep == 1);  
}
//Funciones de ingresar plato de entrada
void platosEntradas(int mesa,int n){
    char opc;
    int cantidad;
    int seguir;
    int j;
    int total;
    int rep;
    do
    {
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("Plato de entrada: \n");  
        printf(" [1] %s precio $ %i \n", platoEntrada[0].nombre, platoEntrada[0].precio);      
        printf(" [2] %s precio $ %i \n", platoEntrada[1].nombre, platoEntrada[1].precio);      
        printf(" [3] %s precio $ %i \n", platoEntrada[2].nombre, platoEntrada[2].precio); 
        printf(" [4] %s precio $ %i \n", platoEntrada[3].nombre, platoEntrada[3].precio);      
        printf(" [0] Inicio\n"); 
        printf("\nIngrese una opcion: ");
        scanf ("%c", &opc);    
 
	    if(opc == '1'){;
            strcpy(pedido[mesa].platoEntrada, platoEntrada[0].nombre);
            total = platoEntrada[0].precio;
            pedido[mesa].total_sale = total + platoEntrada[0].precio; 
            rep =1;           
        }else if( opc == '2'){ 
            strcpy(pedido[mesa].platoEntrada, platoEntrada[1].nombre);
            total = platoEntrada[1].precio;
            pedido[mesa].total_sale = total + platoEntrada[1].precio;
            rep =1;
        }else if(opc == '3'){
            strcpy(pedido[mesa].platoEntrada, platoEntrada[2].nombre);
            total = platoEntrada[2].precio;
            pedido[mesa].total_sale = total + platoEntrada[2].precio;
            rep =1;
        }else if(opc == '4'){
            strcpy(pedido[mesa].platoEntrada, platoEntrada[3].nombre);
            total = platoEntrada[3].precio;
            pedido[mesa].total_sale = total + platoEntrada[3].precio;
            rep =1;
        }else if(opc == '5'){
            rep = 0;
        }else{
            rep =1;
        } 
    } while(rep==1);

    if(rep==0){   
       menuPrincipal(n);
    }
    
    
}
//Funciones de ingresar plato fuerte
void platosFuerte(int mesa,int n){
    char opc;
    int cantidad;
    int seguir;
    int j;
    int total;
    int rep;
    do{
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("Plato fuerte: \n");  
        printf(" [1] %s precio $ %i \n", platoFuerte[0].nombre, platoFuerte[0].precio);      
        printf(" [2] %s precio $ %i \n", platoFuerte[1].nombre, platoFuerte[1].precio);      
        printf(" [3] %s precio $ %i \n", platoFuerte[2].nombre, platoFuerte[2].precio);      
        printf(" [3] %s precio $ %i \n", platoFuerte[3].nombre, platoFuerte[3].precio);      
        printf(" [0] Inicio\n"); 
        printf("\nIngrese una opcion: ");
        scanf ("%c", &opc); 
            if(opc == '1'){;
                strcpy(pedido[mesa].platoFuerte, platoFuerte[0].nombre);
                total = platoFuerte[0].precio;
                pedido[mesa].total_sale = total + platoFuerte[0].precio; 
                rep =1;           
            }else if( opc == '2'){ 
                strcpy(pedido[mesa].platoFuerte, platoFuerte[1].nombre);
                total = platoFuerte[1].precio;
                pedido[mesa].total_sale = total + platoFuerte[1].precio;
                rep =1;
            }else if(opc == '3'){
                strcpy(pedido[mesa].platoFuerte, platoFuerte[2].nombre);
                total = platoFuerte[2].precio;
                pedido[mesa].total_sale = total + platoFuerte[2].precio;
                rep =1;
            }else if(opc == '4'){
                strcpy(pedido[mesa].platoFuerte, platoFuerte[3].nombre);
                total = platoFuerte[3].precio;
                pedido[mesa].total_sale = total + platoFuerte[3].precio;
                rep =1;
            }else if(opc == '5'){
                rep = 0;
            }else{
                rep =1;
            } 
    } while(rep==1);

    if(rep==0){   
        menuPrincipal(n);
    }
}
//Funciones de ingresar postres
void platosPostres(int mesa,int n){
    char opc;
    int cantidad;
    int seguir;
    int j;
    int total;
    int rep;
    do{
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("Postres: \n");  
        printf(" [1] %s precio $ %i \n", postre[0].nombre, postre[0].precio);      
        printf(" [2] %s precio $ %i \n", postre[1].nombre, postre[1].precio);      
        printf(" [3] %s precio $ %i \n", postre[2].nombre, postre[2].precio);         
        printf(" [0] Inicio\n"); 
        printf("\nIngrese una opcion: ");
        scanf ("%c", &opc); 
        if(opc == '1'){;
            strcpy(pedido[mesa].postre, postre[0].nombre);
            total = postre[0].precio;
            pedido[mesa].total_sale = total + postre[0].precio; 
            rep =1;           
        }else if( opc == '2'){ 
            strcpy(pedido[mesa].postre, postre[1].nombre);
            total = postre[1].precio;
            pedido[mesa].total_sale = total + postre[1].precio;
            rep =1;
        }else if(opc == '3'){
            strcpy(pedido[mesa].postre, postre[2].nombre);
            total = postre[2].precio;
            pedido[mesa].total_sale = total +postre[2].precio;
            rep =1;
        }else if(opc == '4'){
            strcpy(pedido[mesa].postre, postre[3].nombre);
            total = postre[3].precio;
            pedido[mesa].total_sale = total + postre[3].precio;
            rep =1;
        }else if(opc == '5'){
            rep = 0;
        }else{
            rep =1;
        } 
    } while(rep==1);

    if(rep==0){   
        menuPrincipal(n);
    }
}
//Funciones de ingresar Bebidas
void platosBebidas(int mesa,int n){
    char opc;
    int cantidad;
    int seguir;
    int j;
     int total;
    int rep;
    do{
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("Bebidas: \n");  
        printf(" [1] %s precio $ %i \n", bebida[0].nombre, bebida[0].precio);      
        printf(" [2] %s precio $ %i \n", bebida[1].nombre, bebida[1].precio);      
        printf(" [3] %s precio $ %i \n", bebida[2].nombre, bebida[2].precio);  
        printf(" [4] %s precio $ %i \n", bebida[3].nombre, bebida[3].precio);       
        printf(" [0] Inicio\n"); 
        printf("\nIngrese una opcion: ");
        scanf ("%c", &opc); 
        if(opc == '1'){;
            strcpy(pedido[mesa].bebida, bebida[0].nombre);
            total = bebida[0].precio;
            pedido[mesa].total_sale = total + bebida[0].precio; 
            rep =1;           
        }else if( opc == '2'){ 
            strcpy(pedido[mesa].bebida, bebida[1].nombre);
            total = bebida[1].precio;
            pedido[mesa].total_sale = total + bebida[1].precio;
            rep =1;
        }else if(opc == '3'){
            strcpy(pedido[mesa].bebida, bebida[2].nombre);
            total = bebida[2].precio;
            pedido[mesa].total_sale = total + bebida[2].precio;
            rep =1;
        }else if(opc == '4'){
            strcpy(pedido[mesa].bebida, bebida[3].nombre);
            total = bebida[3].precio;
            pedido[mesa].total_sale = total + bebida[3].precio;
            rep =1;
        }else if(opc == '5'){
            rep = 0;
        }else{
            rep =1;
        } 
    } while(rep==1);

    if(rep==0){   
        menuPrincipal(n);
    }
}
//Funcion de imprimir factura
void factura(int n){
  char mesa[2];
  int i;
  int rep;
    do{
        system("cls");
        printf("\t\tMIL DELICIAS \n");
        printf("\n Ingresar numero de mesa: ");
        scanf ("%s", &mesa);

        for ( i = 0; i <= n; i++){
        //Compara las cadenas
        if(strcmp(mesa, pedido[i].mesa)==0){
            rep = 0;
            break;
        }else{
           printf("\tMesa no registrada\n");
           getchar();
           rep = 1;
           break;
        }
    }    
    }while(rep == 1);  

    if(rep == 0){
    printf("\t Cliente: %s\n",  pedido[i].cliente);
    printf("\t Mesa: %s\n", pedido[i].mesa);
    printf("\n\t %s", pedido[i].platoEntrada);
    printf("\n\t %s", pedido[i].platoFuerte);
    printf("\n\t %s", pedido[i].postre);
    printf("\n\t %s", pedido[i].bebida);
    printf("\n\t %s \n", pedido[i].total_sale);
    printf("\n Su factura se ha generado con exito! \n ¡GRACIAS POR VISITARNOS!");
    getchar();
    }
    

}
