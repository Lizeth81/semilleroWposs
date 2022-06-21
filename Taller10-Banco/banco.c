#include<stdio.h>
#include<string.h>
#include<stdbool.h>
#include<ctype.h>

#define ENTER 13
#define RETROCESO 8

/*Estructura usuario*/
typedef struct usuario{
    char nombre[20];
    char identificacion[11];
    char correo[25];
    char pass[25];
    int monto;
    char numeroCuenta[11];
    bool estado;
}Usuario;
Usuario user[20]= {{"Lizeth", "1234567888", "lizeth@gmail.com", "1234", 1000000,"257896547", true},
                   {"Paola", "1234567889", "paola@gmail.com", "1234", 1000000, "257896321", true}};
/*Estructura administrador*/
typedef struct administrador{
    char correo[25];
    char pass[10];
}Administrador;
Administrador admin[1] = {{"admin@wposs.com", "Admin123*"}};

/*Funciones*/
void menu();
void opciones(char opcion, int repetir);
void crearUsuario(int n);
char contrasena(char *contrasena);
int camposVacios(char *nombre, char *identidad, char *correo, char *contrasena);
int cantidadUsuario();
int validarNumeros(char *identidad);
bool vcorreo( const char *correo );
int validarContrasena(int n, char *contrasena);
int validarCorreo(int n, char *email);
void ingresarUsuario(int n);
void inicioMenu(int n);
void retirar(int n);
void depositar(int n);
void transferir(int n);
void detalles(int n);
void ingresoAdmin();
void menuadmin(int n);
void listarUsuarios(int n);
void cuentasControl(int n);

/*Funcion principal*/
int main(){
    menu();
    return 0;
}
/*Funcion de menu*/
void menu(){
    char opcion;
    int repetir;	
	do{
		system("cls");
	    printf("\n\t\tBANCO GRUPO TRANSCREDITO");
        printf("\n\t\t========================");
	    printf("\n");
	    printf(" [1]. Crear usuario\n [2]. Ingresar usuario\n [3]. Ingresar administrador\n [0]. Salir\n");
	    printf("\n");
	    printf("Ingrese una opcion: ");
	    scanf ("%c", &opcion);
	    
	    if(opcion == '1' || opcion == '2' || opcion == '3' || opcion == '0'){
	    	 opciones(opcion, repetir);
	    	 break;
		}else{
			repetir=1;
		}  	   
	}while(repetir==1);
}
/*Funcion de opciones del menu*/
void opciones(char opcion, int repetir){
    int n = cantidadUsuario();
	 switch (opcion) {
                case '1':
                crearUsuario(n);
                break;

                case '2': 
                ingresarUsuario(n);
                break;
                
                case '3':
                ingresoAdmin(n);
                break;

                case '0':
                repetir = 0;
                break;
                        
            }
}
/*Funcion de crear usuario*/
void crearUsuario(int n) {
    int ingreso;
    char nombre[20];
    char identidad[11];
    char correo[25];
    char pass[25];

     do{
        system("cls");
        printf("\n\t\tCREAR USUARIO\n");
        printf("\n");
         /*Validar el nombre que no este el campo vacio*/
        printf("\n Nombre: ");
        fflush(stdin);
        gets(nombre);
        /*Validar el campo de numero de identidad, que no este vacio y que sea numerico*/  
        printf("\n N%c de identidad: ", 248);
        fflush(stdin);
        gets(identidad);        
        int numeros = validarNumeros(identidad);
        /*Validar correo, que sea valido y no este vacio*/
        printf("\n Correo electronico: ");
        fflush(stdin);
        gets(correo); 
        bool p=vcorreo(correo);
         /*Validar contraseña, que no este vacia y que solo tenga 8 caracteres*/
        printf("\n Contrase%ca: ", 164);    
        fflush(stdin);
        contrasena(pass);  
        int campoCon = camposVacios(nombre, identidad, correo, pass); 
        int numerocontrasena = strlen(pass);  
        if(campoCon == 0){
            printf("\n\t---> Campo vacio!!!");
            getchar();
            ingreso = 0;

        }if(numeros == 0){
            printf("\n\t---> N%c de identidad incorrecto!!!", 248);
            getchar();
            ingreso = 0;
        }if(p == false){
            printf("\n\t--> Correo incorrecto!!! ");
            getchar();
            ingreso = 0;
        }if(numerocontrasena <= 8){
            printf("\n\t--> La contrase%ca debe tener minimo 8 caracteres.", 164);
            getchar();
            ingreso = 0;
        }else{
            strcpy(user[n].nombre, nombre); 
            strcpy(user[n].identificacion, identidad);
            strcpy(user[n].correo,correo);
            strcpy(user[n].pass, pass);
            user[n].monto = 1000000; 
            long nc = rand () % 1000000000 + 9999999999;
            sprintf(user[n].numeroCuenta, "%d", nc);
            // user[n].numeroCuenta = nc;
            ingreso = 1;
        }   
         
    }while(ingreso == 0);

    if(ingreso == 1){
        printf("\n");
        printf("\n\t %cUSUARIO CREADO CON EXITO!", 173);
        getchar();
        menu();
    }         
}
//Funcion de contraseña de la
char contrasena(char *pass){
    // int n = strlen(contrasena);
    char caracter;
    int i=0;
    while(caracter = getch()){
        if(caracter == ENTER){
            pass[i] = '\0';
            break;
        }else if(caracter == RETROCESO){
            if(i>0){
                i--;
                printf("\b \b");
            }
        }else{
            if(i<9){
                printf("*");
                pass[i] = caracter;
                i++;
            }
        }
    }
}
//Funcion de valida campos vacios
int camposVacios(char *nombre, char *identidad, char *correo, char *contrasena){
   nombre[strcspn(nombre, "\r\n")] = 0;
   identidad[strcspn(identidad, "\r\n")] = 0;
   correo[strcspn(correo, "\r\n")] = 0;
   contrasena[strcspn(contrasena, "\r\n")] = 0;
        if(nombre[0] == '\0' || identidad[0] == '\0' || correo[0] == '\0' || contrasena[0] == '\0') {
           return 0;
        //    printf("\n\t--> Campos vacios!!! \n");
        //    getchar();
        }else {
            return 1;
        }
}
//Funcion para calcular la cantidad de usuarios en
int cantidadUsuario(){
    int n, e=0;
    int i = 0;
    n = sizeof(user)/sizeof(user[0]);
    for(i; i < n; i++){
        if(strcmp(user[i].identificacion, "") != 0){
            e++;
        }
    }
    return e;  
}
//Validar correo
bool vcorreo( const char *correo ) {
    correo = strchr( correo, '@' );
    if(correo == NULL ){
        return false;
        // printf("\n\t--> Correo incorrecto!!! \n");
        // getchar();        
    }
    return strchr( correo, '.' ) != NULL;
    
}
//Funcion solo numeros
int validarNumeros(char *identidad){
	int i;
	for (i=0; i < strlen(identidad); i++){
     if (!(isdigit(identidad[i]))){
         return 0;
     }else{     
        return 1;
     }
 }
}
//Funcion de ingresar usuario
void ingresarUsuario(int n){
    int intentos = 0;
    int ingreso = 0; 
    char email[25];
    char pass[9];
    do{      
       system("cls");
        printf("\n\t\t\tINGRESAR USUARIO\n");
        fflush(stdin);
        printf("\n\t\tCorreo: ");
        gets(email);
        int validar1 = validarCorreo(n, email);
        printf("\n\t\tContrase%ca: ", 164);
        contrasena(pass);         
         int validar2 = validarContrasena(n, pass);        
         if(validar1 == 0 && validar2 == 0){ 
             printf("\n\t %cBIENVENIDO AL SISTEMA!!!", 173); 
             getchar();          
             ingreso = 1;
             
         }else{
            printf("\n");
            printf("\n\t--> Usuario y/o contrasena son incorrectas");
            intentos ++;
            getchar();
         }
    }while(intentos<3 && ingreso==0);

     if(ingreso == 1){
        printf("\n");      
        inicioMenu(n);
    }  
}
//Funcion Validar login       
int validarCorreo(int n, char *email){
    int validar = 1;
    int j=0;
     for(j; j < n; j++){  
        int v1 = strcmp(email, user[j].correo); 
           if(v1 == 0){ 
                validar = 0;
                break;
            }
       }

       return validar;
}     
int validarContrasena(int n, char *contrasena){
    int validar = 1;
    int j=0;
     for(j; j < n; j++){  
        int v2 = strcmp(contrasena, user[j].pass);
           if(v2 == 0){ 
                validar = 0;
                break;
            }
       }
    return validar;
}
//Funcion de inicio
void inicioMenu(int e){
    int repetir;
    char opc;
    int n = e-1;
    do{
        system("cls");
        printf("\n");
        printf("\n\tBIENVENIDO AL BANCO WPOSS");
        printf("\n Usuario: %s \n Numero de cuenta: %s \n Saldo: %i\n", user[n].nombre, user[n].numeroCuenta, user[n].monto);
        printf("\n");
        printf("\nMenu principal: ");
        printf("\n [1].Retirar \n [2].Transferir \n [3].Depositar \n [4].Detalles de la cuenta \n [5].Salir");
        printf("\nIngrese una opcion: ");
        scanf("%c",&opc);

        if(opc == '1'){
            retirar(e);
            repetir = 0;
        }else if(opc == '2'){
            transferir(e);
            repetir = 0;
        }else if(opc == '3'){
            depositar(e);
            repetir = 0;
        }else if(opc == '4'){
            detalles(e);
            repetir = 0;
        }else if(opc == '5'){
            menu();
            repetir = 0;
        }else{
            repetir = 1;
        }
    } while (repetir==1);
}
//Funcion de retirar dinero
void retirar(int e){
    int monto,repetir;
    int n = e-1;
    do{
        system("cls");
        printf("\n\tBANCO WPOSS");
        printf("\n Usuario: %s \n Numero de cuenta: %s \n Saldo: %i\n", user[n].nombre, user[n].numeroCuenta, user[n].monto);
        printf("\n Realizar retiro de dinero");
        printf("\n");
        printf("\n Monto a retirar: ");
        scanf("%i",&monto);
        if (user[n].monto < monto){
            printf("No tienes saldo suficiente para realizar el retiro.");
            repetir=1;
        }else{
            user[n].monto-= monto;
            printf("El retiro se realizo con exito!\n");
            printf("\n Usuario %s, nuevo saldo %i", user[n].nombre, user[n].monto);
            getchar();
            inicioMenu(e);
        }
    } while (repetir==1);   
}
//funcion de depositar dinero
void depositar(int e){
    int monto;
    int n = e-1;
    system("cls");
    printf("\n\tBANCO WPOSS");
    printf("\n Usuario: %s \n Numero de cuenta: %s \n Saldo: %i\n", user[n].nombre, user[n].numeroCuenta, user[n].monto);
    printf("\n Realizar deposito de dinero");   
    printf("\n");
    printf("\nIndique el monto a depositar: ");
    scanf("%i",&monto);
    user[n].monto += monto;
     printf("\nSu transacción se realizo con exito!");
    printf("Usuario %s, nuevo saldo %i", user[n].nombre, user[n].monto);
    getchar();
    inicioMenu(e);
}
//funcion de transferir dinero
void transferir(int e){
    int monto,k,repetir = 0;
    char numeroCuentaTrans[20];
    int n = e-1;
    do{
        int x, k;
        system("cls");
        printf("\n\tBANCO WPOSS");
        printf("\n Usuario: %s \n Numero de cuenta: %s \n Saldo: %i\n", user[n].nombre, user[n].numeroCuenta, user[n].monto);
        printf("\n Realizar transferencia de dinero");   
        printf("\n");
        printf("\nIndique el monto a tranferir: ");
        scanf("%i",&monto);    
        printf("\nNumero de cuenta a la cual transferir: ");
        scanf("%s",&numeroCuentaTrans);

        if (user[n].monto < monto){
            printf("\nNo tienes saldo suficiente para realizar la tranferencia.");
            repetir=1;
        }
        for (int i = 0; i < e; i++){
           x=strcmp(numeroCuentaTrans, user[i].numeroCuenta);
           if (x==0){
               repetir = i;
               break;
           }
        }

        if (x==0){
            user[n].monto -= monto;
            printf("repite: %i", repetir);
            user[repetir].monto += monto;
            printf("\nTransferencia con exito!");
            printf("\nUsuario %s, nuevo saldo %i", user[n].nombre, user[n].monto);
            getchar();
            inicioMenu(e);
        }else{
            printf("\nNumero de cuenta no encontrado, intente de nuevo");
            memset(numeroCuentaTrans, 0, sizeof(numeroCuentaTrans));
            repetir=1;
        }
        
    }while(repetir==1);
}
//Funcion de mostrar del usuario
void detalles(int e){
    int n = e-1;
    system("cls");
    printf("\n\tBANCO WPOSS");
    printf("\n Usuario: %s \n Numero de cuenta: %s \n Saldo: %i \n Correo electronico: %s \n Numero de identidad: %s \n", user[n].nombre, user[n].numeroCuenta, user[n].monto, user[n].correo, user[n].identificacion);
    if (user[n].estado==true){
        printf("\nEstado de la cuenta activo: ");
    }else{
        printf("\nEstado de la cuenta desactivado");
    }
    printf("\n ENTER para continuar...");
    getchar();
    inicioMenu(e);
}



/*******Ingreso del Admin*****/
void ingresoAdmin(int n){
    int a = 0;
    int intentos = 0;
    int ingreso = 0; 
    char email[16];
    char pass[9];
    do{
        system("cls");
        printf("\n\t\t\tINGRESAR ADMINISTRADOR\n");
        printf("\n\t\tCorreo: ");   
        fflush(stdin);     
        gets(email);
        if(strcmp(email, admin[0].correo) == 0){ 
        ingreso = 1;
        }else {
        printf("\n");
        printf("\n\t--> Correo incorrecto");
        intentos ++;
        getchar();      
        }
    } while (intentos<3 && ingreso==0);
    do{
        system("cls");
        printf("\n\t\t\tINGRESAR ADMINISTRADOR\n");
        printf("\n\t\tCorreo: %s", email);   
        fflush(stdin);     
        printf("\n\t\tContrase%ca: ", 164);
        contrasena(pass);
        if(strcmp(pass, admin[0].pass) == 0){ 
            ingreso = 1;
            printf("\n\t %cBIENVENIDO AL SISTEMA!!!", 173); 
            getchar(); 
        }else {
            printf("\n");
            printf("\n\t--> Contraseña incorrecto");
            intentos ++;
            getchar();      
        }
    } while (intentos<3 && ingreso==0);
    
    // do{      
    //    system("cls");
    //     printf("\n\t\t\tINGRESAR ADMINISTRADOR\n");
    //     printf("\n\t\tCorreo: ");   
    //     fflush(stdin);     
    //     gets(email);
    //     printf("\ncorreo: %s", email);
    //     fflush(stdin);        
    //     printf("\n\t\tContrase%ca: ", 164);
    //     contrasena(pass);   
    //     fflush(stdin);      
    //     int validar = validarUsuarioAdmin(email, pass);
    //     printf("\ncorreo: %s", email);  
    //     if(validar == 0){ 
    //          printf("\n\t %cBIENVENIDO AL SISTEMA!!!", 173); 
    //          getchar();          
    //          ingreso = 1;
    //      }else{
    //         printf("\n");
    //         printf("\n\t--> Usuario y/o contrasena son incorrectas");
    //         intentos ++;
    //         getchar();
    //      }
    // }while(intentos<3 && ingreso==0);
    // // Direcciona al sistema o al menu 
    if (ingreso == 1){
        menuadmin(n);
    }else{
        menu();
    }
}
void menuadmin(int n){
    int repetir = 0;
    char opc;
    do{
        system("cls");
        printf("\nBienvenido administrador.\n");
        printf("\n [1].Listar usuarios.\n [2].Inhabilitar / Habilitar cuentas \n [0]. Salir");
        printf("\nIngresa una opcion: ");
        scanf("%c",&opc);

        if(opc=='1'){
            listarUsuarios(n);
        }else if(opc=='2'){
            cuentasControl(n);
        }else if(opc=='0'){
            menu();
        }else{
            repetir = 1;
        }
    } while (repetir==1);   

}
void listarUsuarios(int n){
    for (int i = 0; i < n; i++){
        printf("\nNumero de cuenta: %d", user[i].numeroCuenta);
        printf("\nNombre titular de la cuenta: %s",user[i].nombre);
        printf("\nCorreo titular de la cuenta: %s",user[i].correo);
        printf("\nDocumento de identidad de la cuenta: %s",user[i].identificacion);
        printf("\nSaldo de la cuenta: %i",user[i].monto);
        if (user[i].estado==true){
            printf("\nEstado de la cuenta: Activo");
        }else{
            printf("\nEstado de la cuenta: Desactivado");
        }
        printf("\n ------------------------------------------------------\n");
    }
    fflush(stdin);
    printf("\n\nENTER para continua...");
    getchar();
    menuadmin(n);
}
void cuentasControl(int n){
    int rep,p,r;
    char numCuenta[11];
    do{
        int x;
        system("cls");        
        printf("\nBienvenido al sistema de inhabilitar o habilitar un Usuario\n");
        printf("\nIntresar el numero de cuenta que desea inhabilitar\n");
        printf("\nNumero de cuenta: ");
        scanf("%s", &numCuenta);

        for (int i = 0; i < n; i++){            
            x = strcmp(numCuenta, user[i].numeroCuenta);
            if (x == 0){
                p=i;
                break;
            }
        }
        if (x == 0){
            printf("\n [1] Desactivar cuenta\n [2] Activar cuenta");
            printf("\nIngrese una opcion: ");
            scanf("%i",&r);
            r-=1;
            user[p].estado=r;
            if (user[p].estado==true){
                printf("\nEstado de la cuenta: Activo");
            }else{
                printf("\nEstado de la cuenta: Desactivado");
            }
            menu();
        }else{
            rep=1;
        }        
    } while (rep==1);
}


