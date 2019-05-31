/*Jacob Elliott 0835468 Question One Assignment Two*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>


/*
 Structure defining car
 */
typedef struct car{
    char * plateNumber;
    int returnDate;
    int mileage;
    struct car * next;
}car;

/* 
 *This function will initialize the car using PlateNumber, mileage, and return date which should always be
 *zero when the car is being created.
 */
car * initCar(char * plateNumber, int mileage, int returnDate);
/*
 This function will add a car to a certain list, it takes in the car to add and the head of the list,
 and it returns the head of the new list.
 */
car * addCar(car * head, car * carToAdd);

/*
 This function will add to a list that is sorted by mileage, the cars with lowest mileage will be placed first. It also takes in the head of the list, a car, and the mileage. It returns the head of the new list.
 */
car * addCarMileage(car * head, car * carToAdd, int mileage);

/*
 This function will add a car based on return date. The list is sorted based on return date. It takes in the head of the list the car to add and the return date, and it returns the head of the new list.
 */
car * addCarReturnDate(car * head, car * carToAdd, int returnDate);

/*
 This function prints a list, it takes in the head of a list and returns the head of the list.
 */
car * printList(car * head);

/*
 This function removes a car from a list based on the licence plate. It takes in the address of the head of the list and the licence number, and it return the element that was removed.
 */
car * removeCar(car ** head, char * licenceNumber);

/*
This function removes a car from a list based on licence plate and mileage. It takes in the address of the head of the list and the licence number and the mileage, and returns the removed element.
 */
car * removeCarWithMileage(car ** head, char * licenceNumber, int mileage);

/*
 This function checks the input, its supposed to be an integer, it will return -1 if the string entered cannot be casted to an integer.
 */
int checkInput(char * string);

/*This function is used if the input is a double and a period may be added to the number*/
int checkInputNum(char * string);

int checkInputReturn(char * string);

/*This function will calculate the cost of the car based on the mileage*/
double calculateCost(int i);
 /*This function takes in a string and removes all the new-line character in the list and replaces them with null terminators*/
char * removeNewLineChar(char * string);

/*This function creates the list of cars based on the text file. It takes in the address of the three lists and reads from a file and creates the lists*/
void loadCars(car ** headOfToRent,car ** headOfRepair,car ** headOfRented);
/*This function will print all three lists out to a file overwriting all the information in that file.*/
void printOutList(car * headOfToRent,car * headOfRented,car * headOfRepair);
/*This function will free a whole list given the head of that list.*/
void freeList(car * head);

void test();

int main()
{
    test();
    return 1;
}
void test()
{
    car * headOfToRent = NULL;
    car * headOfRepair = NULL;
    car * headOfRented = NULL;
    
    int selection;
    char input [100];
    
    
    char licencePlate [100];
    char * newLicencePlate;
    int mileage ;
    int returnDate;
    int totalForTheDay = 0;
    
    returnDate = 0;
    mileage = 0;
    selection = 0;
    /*We load all the cars into the list based on the file.*/
    loadCars(&headOfToRent,&headOfRepair,&headOfRented);

    /*We do this while selection is not equal to quit.*/
    while(selection != 7)
    {
        do
        {
            printf("Hello, please select a transaction code.\n");
            printf("1. Add a new car to rent.\n");
            printf("2. Return a car to rent.\n");
            printf("3. Return a car to repair list.\n");
            printf("4. Transfer a car from repair to rent.\n");
            printf("5. Rent the first car\n");
            printf("6. Display cars for rent, cars rented, and cars for repair.\n");
            printf("7. Quit\n");
            fgets(input,100,stdin);
        }while((selection = checkInput(input)) == -1);
        
        switch(selection)
        {
            /*If you want to add a new car to the rent this.*/
            case 1:
            {
                printf("Please enter the following information.\n");
                do
                {
                printf("Licence Plate information : ");
                fgets(licencePlate,100,stdin);
                
                newLicencePlate = removeNewLineChar(licencePlate);
                
                }while(strcmp(licencePlate,"\n")  == 0);
              
                do
                {
                    printf("Mileage number (km) : ");
                    fgets(input,100,stdin);
                }while((mileage = checkInputNum(input)) == -1);
                
                returnDate = 0;
                
                car * newCar = initCar(newLicencePlate,mileage,returnDate);
                
                if(newCar != NULL)
                {
                    headOfToRent = addCarMileage(headOfToRent,newCar,mileage);
                    printf("Car successfully added.\n");
                }
                else
                {
                    printf("Unable to add car!\n");
                }
                
            }
                break;
            case 2:/*Return a car to rent.*/
            {
                
                if(headOfRented != NULL)
                {
                    
                    printf("Please enter the following information.\n");
                    printf("Licence Plate information : ");
                    fgets(licencePlate,100,stdin);
                    /*Gets the licence plate information from the user.*/
                    newLicencePlate = removeNewLineChar(licencePlate);
                    do
                    {
                        /*Gets the mileage number from the user.*/
                        printf("Mileage number (km) : ");
                        fgets(input,100,stdin);
                        strcpy(input,removeNewLineChar(input));
                   
                    }while((mileage = checkInputNum(input)) == -1);
                    
                    /*Creates a new car pointer and removes a car from the list with mileage*/
                    car * newCar = removeCarWithMileage(&headOfRented, newLicencePlate, mileage);
                    if(newCar != NULL)
                    {
                        /*If the new car is not null we have found a car and have removed it*/
                        printf("The cost for the car is : %0.2lf\n",calculateCost(newCar->mileage));
                        totalForTheDay += calculateCost(newCar->mileage);
                        newCar -> returnDate = 0;
                        headOfToRent = addCar(headOfToRent,newCar);
                        printf("Car successfully moved.\n");
                    }
                    else
                    {
                        printf("Unable to move car!\n");
                    }
                }
                else
                {
                    printf("You cannot move a car from an empty list.\n");
                }
            }
                break;
            case 3: /*Return a car from rented to repair*/
            {
                if(headOfRented != NULL)
                {
                    /*Prompting for licence plate information*/
                    printf("Please enter the following information.\n");
                    printf("Licence Plate information : ");
                    fgets(licencePlate,100,stdin);
                    /*removing the new line character from the licence plate*/
                    newLicencePlate = removeNewLineChar(licencePlate);
                    do
                    {
                        printf("Mileage number (km) : ");
                        fgets(input,100,stdin);
                        strcpy(input,removeNewLineChar(input));
                    }while((mileage = checkInputNum(input)) == -1);
                    
                    strcpy(licencePlate,removeNewLineChar(licencePlate));
                    car * newCar = removeCarWithMileage(&headOfRented,licencePlate,mileage);
                    newCar ->returnDate = 0;
                    
                    /*If the new car is null then we haven't the car based on the licence plate*/
                    if(newCar != NULL)
                    {
                        /*If we reach this point we have found the licence plate and we can calcuate the cost of the car and return the car for repairs.
                         */
                        printf("The cost for the car is : %0.2lf\n",calculateCost(newCar->mileage));
                        totalForTheDay += calculateCost(newCar->mileage);
                        headOfRepair = addCar(headOfRepair,newCar);
                        printf("Car successfully moved.\n");
                    }
                    else
                    {
                        printf("Unable to move car!");
                    }
                }
                else
                {
                    printf("You cannot move a car from an empty list.\n");
                }
            }
                break;
            case 4: /*Transfer a car from repair to rent*/
            {
                if(headOfRepair != NULL)
                {
                    printf("Please enter the following information.\n");
                    printf("Licence Plate information : ");
                    fgets(licencePlate,100,stdin);
                    newLicencePlate = removeNewLineChar(licencePlate);
                    car * theCar = removeCar(&headOfToRent,newLicencePlate);
                    if(theCar != NULL)
                    {
                    headOfToRent = addCar(headOfToRent,theCar);
                    printf("Car was moved successfully.\n");
                    }
                    else
                    {
                        printf("Could not find the car.\n");
                    }
                }
                else
                {
                    printf("You cannot transfer a car from an empty list.\n");
                }
            }
                break;
            case 5: /*Move the first car in to rent to rented*/
            {
                if(headOfToRent != NULL)
                {
                    car * theCar = removeCar(&headOfToRent,headOfToRent->plateNumber);
                    do
                    {
                        printf("Return Date (days) : ");
                        fgets(input,100,stdin);
                        strcpy(input,removeNewLineChar(input));
                    }while((returnDate = checkInputReturn(input)) == -1);

                    
                    theCar->returnDate = returnDate;
                    headOfRented = addCar(headOfRented,theCar);
                    printf("\nCar was successfully moved.\n");
                }
                else
                {
                    printf("Cannot move a car from an empty list.\n");
                }
                
            }
                break;
            case 6:
            {
                printf("~~~~TO RENT~~~~\n");
                printList(headOfToRent);
                printf("~~~~RENTED~~~~\n");
                printList(headOfRented);
                printf("~~~~TO REPAIR~~~~\n");
                printList(headOfRepair);
            }
                break;
            case 7:
                
                printOutList(headOfToRent,headOfRented,headOfRepair);
                freeList(headOfToRent);
                freeList(headOfRented);
                freeList(headOfRepair);
                break;
            default:
                break;
        }
    }
    
}

/*This function frees the list using the head of a certain list.*/
void freeList(car * head)
{
    car * current = head;
    car * next;
    
    while(current != NULL)
    {
        next = current->next;
        free(current->plateNumber);
        free(current);
        current = next;
    }
}

car * initCar(char * name, int lice, int returnDate)
{
    car * newCar;
    newCar = malloc(sizeof(car));
    
    newCar->plateNumber = malloc(strlen(name)+1);
    strcpy(newCar->plateNumber,name);
    newCar->mileage = lice;
    newCar->returnDate = returnDate;
    return newCar;
}

car * addCarMileage(car * head, car * carToAdd, int mileage)
{
    car * current;
    if(head == NULL || head->mileage >= mileage)
    {
        carToAdd->next = head;
        head = carToAdd;
        return head;
    }
    else
    {
        current = head;
        while(current->next!=NULL && current->next->mileage < carToAdd->mileage)
        {
            current = current -> next;
        }
        carToAdd->next = current->next;
        current->next = carToAdd;
        return head;
    }
    
}

car * addCarReturnDate(car * head, car * carToAdd, int returnDate)
{
    car * current;
    if(head == NULL || head->mileage >= returnDate)
    {
        carToAdd->next = head;
        head = carToAdd;
        return head;
    }
    else
    {
        current = head;
        while(current->next!=NULL && current->next->returnDate < carToAdd->returnDate)
        {
            current = current -> next;
        }
        carToAdd->next = current->next;
        current->next = carToAdd;
        return head;
    }
    
}


car * addCar(car * head, car * carToAdd)
{
    car * dummy = head;
    if(carToAdd == NULL)
    {
        return head;
    }
    if(head == NULL)
    {
        head = carToAdd;
        return head;
    }
    while(dummy->next != NULL)
    {
        dummy = dummy->next;
    }
    dummy->next = carToAdd;
    
    return head;
}

car * printList(car * head)
{
    int i = 1;
    if(head == NULL)
    {
        return NULL;
    }
    car * next = head;
    do
    {
        printf("%d : Plate Number : %s Mileage : %d Return Date : %d\n",i,next->plateNumber, next->mileage, next->returnDate);
        i++;
    }while((next = next->next) != NULL);
    return head;
}


car * removeCar(car ** head, char * licenceNumber)
{
    if(strcmp((*head)->plateNumber,licenceNumber) == 0)
    {
        car * temp = *head;
        *head = (*head)->next;
        temp->next = NULL;
        return temp;
    }
    
    car * current = (*head)->next;
    car * previous = *head;
    while(current != NULL && previous != NULL)
    {
        if(strcmp(current->plateNumber,licenceNumber) == 0)
        {
            car * temp = current;
            previous->next = current->next;
            temp->next = NULL;
            return temp;
        }
        previous = current;
        current = current->next;
    }
    return NULL;
}

car * removeCarWithMileage(car ** head, char * licenceNumber, int mileage)
{
    if(strcmp((*head)->plateNumber,licenceNumber) == 0 && ((*head)->mileage == mileage))
    {
        car * temp = *head;
        *head = (*head)->next;
        temp->next = NULL;
        return temp;
    }
    
    car * current = (*head)->next;
    car * previous = *head;
    while(current != NULL && previous != NULL)
    {
        if(strcmp(current->plateNumber,licenceNumber) == 0 && ((*head)->mileage == mileage))
        {
            car * temp = current;
            previous->next = current->next;
            temp->next = NULL;
            return temp;
        }
        previous = current;
        current = current->next;
    }
    return NULL;
}


int checkInput(char * string)
{
    int i;
    int test;
    int canCast = 1;
    
    if(strcmp(string,"\n") == 0)
    {
        return -1;
    }
    for(i = 0; i < strlen(string); i ++)
    {
        if(!isdigit(string[i]) && !(string[i] == '\0') && !(string[i] == '\n'))
        {
            canCast = 0;
        }
    }
    if(canCast == 1)
    {
        test = atoi(string);
        if(test > 7 || test < 1)
        {
            return -1;
        }
        else
        {
            return test;
        }
    }
    else
    {
        return (-1);
    }
}

int checkInputNum(char * string)
{
    int i;
    int canCast = 1;
    if(strcmp(string,"\n") == 0)
    {
        return -1;
    }
    for(i = 0; i < strlen(string); i ++)
    {
        if(!isdigit(string[i]) && !(string[i] == '\0') && !(string[i] == '.') && !(string[i] == '\n'))
        {
            canCast = -1;
        }
    }
    if(canCast == 1)
    {
        return(atoi(string));
    }
    else
    {
        return (-1);
    }
}
int checkInputReturn(char * string)
{
    int i;
    int canCast = 1;
    if(strlen(string) != 6)
    {
        printf("Return date must be 6 digits in length [yymmdd]");
        return -1;
    }
    if(strcmp(string,"\n") == 0)
    {
        return -1;
    }
    for(i = 0; i < strlen(string); i ++)
    {
        if(!isdigit(string[i]) && !(string[i] == '\0') && !(string[i] == '.') && !(string[i] == '\n'))
        {
            canCast = -1;
        }
    }
    if(canCast == 1)
    {
        return(atoi(string));
    }
    else
    {
        return (-1);
    }
}


void loadCars(car ** headOfToRent,car ** headOfRepair,car ** headOfRented)
{
    FILE * f;
    char line[100];
    
    f = fopen("list.txt","r");
    if(f != NULL)
    {
        while(fgets(line,100,f) != NULL)
        {
            char * nextLine = strtok(line,",");
            while(nextLine != NULL)
            {
                char * licencePlate;
                char * mileageString;
                char * returnDateString;
                int mileage = 0;
                int returnDate = 0;
                
                if(strcmp(nextLine,"rent") == 0)
                {
    
                    licencePlate = strtok(NULL,",");
                    mileageString = strtok(NULL,",");
                    returnDateString = strtok(NULL,",");
                    
                    strcpy(mileageString,removeNewLineChar(mileageString));
                    strcpy(returnDateString,removeNewLineChar(returnDateString));
                    
                    returnDate = checkInputNum(returnDateString);
                    mileage = checkInputNum(mileageString);
                    
            
                    if(mileage < 0)
                    {
                        mileage = 0;
                    }
                    
                    (*headOfToRent) = addCarMileage((*headOfToRent),initCar(licencePlate,mileage,returnDate),mileage);
                }
                else if(strcmp(nextLine,"repair") == 0)
                {
                    licencePlate = strtok(NULL,",");
                    mileageString = strtok(NULL,",");
                    returnDateString = strtok(NULL,",");
                    
                    strcpy(mileageString,removeNewLineChar(mileageString));
                    strcpy(returnDateString,removeNewLineChar(returnDateString));
                    
                    returnDate = checkInputNum(returnDateString);
                    mileage = checkInputNum(mileageString);
                   
                    if(mileage < 0)
                    {
                        mileage = 0;
                    }
                    (*headOfRepair) = addCar((*headOfRepair),initCar(licencePlate,mileage,returnDate));
                }
                else if (strcmp(nextLine,"rented") == 0)
                {
                    licencePlate = strtok(NULL,",");
                    mileageString = strtok(NULL,",");
                    returnDateString = strtok(NULL,",");
                    
                    strcpy(mileageString,removeNewLineChar(mileageString));
                    strcpy(returnDateString,removeNewLineChar(returnDateString));
                    
                    
                    returnDate = checkInputNum(returnDateString);
                    
                    mileage = checkInputNum(mileageString);
                    
                    if(mileage < 0)
                    {
                        mileage = 0;
                    }
                    (*headOfRented) = addCarReturnDate((*headOfRented),initCar(licencePlate,mileage,returnDate),returnDate);
                }
                nextLine = strtok(NULL,",");
            }
        }
    }
    fclose(f);
}
void printOutList(car * headOfToRent,car * headOfRented,car * headOfRepair)
{
    FILE * f;
    f = fopen("list.txt","w");
    double total = 0.00;
    if(f != NULL)
    {
        
        car * current = headOfToRent;
        car * next;
        while(current != NULL)
        {
            next = current->next;
            current -> plateNumber = removeNewLineChar(current->plateNumber);
            fprintf(f,"rent,%s,%d,%d\n",current->plateNumber,current->mileage,current->returnDate);

            current = next;
        }
        current = headOfRented;
        while(current != NULL)
        {
            total += calculateCost(current->mileage);
            next = current->next;
            current->plateNumber = removeNewLineChar(current->plateNumber);
            fprintf(f,"rented,%s,%d,%d\n",current->plateNumber,current->mileage,current->returnDate);
            
            current = next;
        }
        current = headOfRepair;
        while(current != NULL)
        {
            next = current->next;
            current->plateNumber = removeNewLineChar(current->plateNumber);
            fprintf(f,"repair,%s,%d,%d\n",current->plateNumber,current->mileage,current->returnDate);
            
            current = next;
        }
        printf("The total is : %0.2lf\n",total);
        fclose(f);
    }
    
}


char * removeNewLineChar(char * string)
{
    int i;
    char * newString = malloc(sizeof(string));
    
    for(i = 0; i < strlen(string); i ++)
    {
        if(!strcmp(&string[i],"\n") == 0)
        {
            newString[i] = string[i];
        }
    }
    
    
    return newString;
}

double calculateCost(int mileage)
{
    /* if mileage > 100, (40 + (mileage - 100) * (0.15)) otherwise return 40*/
    if(mileage > 40)
    {
        return (40 + (mileage - 100) * (0.15));
    }
    else
    {
        return 40;
    }
}
