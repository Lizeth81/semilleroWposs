#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*Estructuras de los datos de los libros*/
typedef struct book_data{
    char name[30];
    int price; //precio
    int quantity; //Cantidad
}Book;
Book book[6]={{"Las luces de Septiembre", 24000, 0},                           
              {"El principe de la Niebla", 28000, 0},
              {"El Palacio de Medianoche", 45000, 0},
              {"Marina", 15000, 0},
              {"La Sombra del Viento", 27000, 0},
              {"El Juego del Angel", 30000, 0}};

/*Estructuras de los datos de las casetas*/
typedef struct booth_data{
    char name[20];
    int quantity; //Cantidad
    int total_sale; //total de la venta
}Booth;
Booth booth[3]={{"Caseta 1", 0, 0},                           
              {"Caseta 2", 0, 0},
              {"caseta 3", 0, 0}};

Book numBooks; 
Booth numBooch; 


void Menu();
void Opciones(char opcion, int repetir);
void RealizarVenta();
void SelecccionarLibro(int caseta);
void ImprimirVenta();
void GuardarValore(int caseta, int libro);
void VentaCaseta();
//void CantidadLibro();
void FiltroLibro();
void FiltroCaseta();

int main(){
    Menu();
	return 0;
}
/*Funcion de menu*/
void Menu(){
    char opcion;
    int repetir;	
	do{
		system("cls");
	    printf("\tBIENVENIDOS A LA VENTA DE LIBROS\n");
	     printf("\t================================\n");
	    printf("\n");
	    printf(" [1]. Realizar venta\n");
	    printf(" [2]. Imprimir ventas\n");
	    printf(" [0]. Salir");
	    printf("\n");
	    printf("Ingrese una opcion: ");
	    scanf ("%c", &opcion);
	    
	    if(opcion == '1' || opcion == '2' || opcion == '0'){
	    	 Opciones(opcion, repetir);
	    	 break;
		}else{
			repetir=1;
		}  	   
	}while(repetir==1);
}
//Funcion de opciones
void Opciones(char opcion, int repetir){
	 switch (opcion) {
                case '1':
                RealizarVenta();
                break;

                case '2': 
                ImprimirVenta();
                break;
                
                case '0':
                repetir = 0;
                break;
                        
            }
}
//Funcion de realizar ventas
void RealizarVenta(int numValor, int numcantidad, int cantidadLibro){
char opcion; 
int repetir;
int caseta;

   do{
       system("cls");
       printf("\nSeleccione la caseta:");
       printf("\b\n [1]. Caseta N%c1 \n [2]. Caseta N%c2 \n [3]. Caseta N%c3 \n [4]. Salir \n ", 248, 248, 248); 
       printf("\n Digite una opcion: ");
       scanf("%c", &opcion);

       if(opcion == '1') { 
           caseta = 1;
           SelecccionarLibro(caseta);
           break;
       }if(opcion == '2'){
           caseta = 2;
           SelecccionarLibro(caseta);
           break;
       }if(opcion == '3'){
           caseta = 3;
           SelecccionarLibro(caseta);
           break;
       }if(opcion == '4'){
           Menu(numValor, numcantidad, cantidadLibro);
           break;
       }else{
           repetir = 1;
       }
   }while(repetir == 1);
}
/*Funcion de compra de la caseta */
void SelecccionarLibro(int caseta){
    char opcion; 
    int repetir;
    int libro;    
    do{
        system("cls");
        printf("\n Seleccione el libro a comprar:\n");
        printf("\b [1]. Las luces de Septiembre -valor: 24.000 \n [2]. El principe de la Niebla, -valor: 28.000, \n [3]. El Palacio de Medianoche -valor: 45.000 \n [4]. Marina -valor: 15.000 \n [5]. La Sombra del Viento -valor: 27.000 \n [6]. El Juego del Angel -valor: 30.000 \n [7]. Salir \n "); 
        printf("\n Digite una opcion: ");
        scanf("%c", &opcion);
        if(opcion == '1') { 
           libro = 1;
           printf("\nLibro vendido con exito!!!");
           getchar();
           GuardarValore(caseta, libro);
           break;         
       }if(opcion == '2') {
           libro = 2;
           printf("\nLibro vendido con exito!!!");
           GuardarValore(caseta, libro); 
           break;
       }if(opcion == '3'){
           libro = 3;
           printf("\nLibro vendido con exito!!!");
           GuardarValore(caseta, libro); 
           break;
       }if(opcion == '4'){
            libro = 4;
            printf("\nLibro vendido con exito!!!");
            GuardarValore(caseta, libro); 
            break;
       }if(opcion == '5'){
            libro = 5;
            printf("\nLibro vendido con exito!!!");
            GuardarValore(caseta, libro); 
            break;
       }if(opcion == '6'){
            libro = 6;
            printf("\nLibro vendido con exito!!!");
            GuardarValore(caseta, libro); 
            break;
       }if(opcion == '7'){
           Menu();
           break;
       }else{
           repetir = 1;
       }
    }while(repetir == 1);
   

}
/* funcion de guardar los valores */
void GuardarValore(int caseta, int libro){
    int numValor;
    int numCantidad;
    int CantidadLibro;
    
    if(caseta == 1){
        /*Caseta N° 1*/
        numValor = book[libro-1].price; //almacena en la variable el valor del libro
        booth[caseta-1].total_sale = numValor + booth[caseta-1].total_sale; //suma los valores del libro
        booth[caseta-1].quantity = 1 + booth[caseta-1].quantity; // nueva cantidad de libro 
        book[libro-1].quantity = 1 + book[libro-1].quantity;
        Menu();
               
    }if(caseta == 2){
       /*Caseta N° 2*/
        numValor = book[libro-1].price; //almacena en la variable el valor del libro
        booth[caseta-1].total_sale = numValor + booth[caseta-1].total_sale; //suma los valores del libro
        booth[caseta-1].quantity = 1 + booth[caseta-1].quantity; // nueva cantidad de libro 
        book[libro-1].quantity = 1 + book[libro-1].quantity;
        Menu();

    }if(caseta == 3){
        /*Caseta N° 3*/
        numValor = book[libro-1].price; //almacena en la variable el valor del libro
        booth[caseta-1].total_sale = numValor + booth[caseta-1].total_sale; //suma los valores del libro
        booth[caseta-1].quantity = 1 + booth[caseta-1].quantity; // nueva cantidad de libro 
        book[libro-1].quantity = 1 + book[libro-1].quantity;
        Menu();
    }else{

    }

}
/* funcion de Imprimir Venta*/
void ImprimirVenta(){
    char opcion;
    int repetir;
   do{
       system("cls");
       printf("\n Seleccione el libro a comprar:\n");
       printf("\b [1]. Venta total de cada caseta \n [2]. Filtro por libros  \n [3]. Filtro por caseta \n [4]. Salir \n "); 
       printf("\n Digite una opcion: ");
       scanf("%c", &opcion);
       if(opcion == '1'){
           VentaCaseta();
           break;
       }if(opcion == '2'){            
           FiltroLibro();  
           break;
       } if(opcion == '3'){            
           FiltroCaseta();        
           break;
       }if(opcion == '4'){            
           Menu();        
           break;
       }else{
           repetir = 1;
       }
   }while(repetir == 1);
}
/*venta por caseta*/
void VentaCaseta(){
    system("cls");
    printf("\n\tVENTA POR CASETA");
    printf("\n\t-----------------");
    printf("\n\t          CANTIDAD DE LIBRO   TOTAL");
    printf("\n\t %s        %i           %i", booth[0].name, booth[0].quantity, booth[0].total_sale);
    printf("\n\t %s        %i           %i", booth[1].name, booth[1].quantity, booth[1].total_sale);
    printf("\n\t %s        %i           %i", booth[2].name, booth[2].quantity, booth[2].total_sale);
    printf("\n"); 
    fflush(stdin);
    getchar();
    ImprimirVenta();
}
/*Ordenar de mayor a menor la venta de cada libro */
void FiltroLibro(){
    // Ordenar de mayor a menor la venta de cada libro y mostrar en opción 
    int x = 0;
    int i = 0;
    int k = 0;
    for (x; x < 6; x++){
        for (i; i < 6 - 1; i++){
            int j = i + 1;
            // Ordenar de manera descendente
            if (book[i].quantity < book[j].quantity){
                // Intercambiar
                memcpy(&numBooks, &book[i], sizeof(Book));
                memcpy(&book[i], &book[j], sizeof(Book));
                memcpy(&book[j], &numBooks, sizeof(Book));
            }
        }
    }
    for (k; k < 6; k++){
        printf("\n%s",book[k].name);
        printf("\nCantidad de libros vendidos:%d ", book[k].quantity);
        printf("\n");  
        fflush(stdin);  
    }
    getchar(); 
    ImprimirVenta();
}
/*Ordenar de menor a mayor la venta de cada caseta*/
void FiltroCaseta(){
	int x = 0;
	int i = 0;
	int k = 0;
    for (x; x < 3; x++){
        for (i; i < 3 - 1; i++){
            int j = i + 1;
            // Ordenar de manera ascendente
            if (booth[i].quantity > booth[j].quantity)
            {
                // Intercambiar
                memcpy(&numBooch, &booth[i], sizeof(Booth));
                memcpy(&booth[i], &booth[j], sizeof(Booth));
                memcpy(&booth[j], &numBooch, sizeof(Booth));
            }
        }
    }
    for (k ; k < 3; k++){
        printf("\n%s",booth[k].name);
        printf("\nCantidad de libros vendidos es :%d ", booth[k].quantity);
        printf("\nTotal de ventas por caseta es : %d",booth[k].total_sale); 
        printf("\n");  
        fflush(stdin);
          
    }
    getchar();  
    ImprimirVenta();
}
