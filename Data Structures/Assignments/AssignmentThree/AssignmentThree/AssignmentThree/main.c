#include <stdio.h>#include <string.h>#include <stdlib.h>#include <ctype.h>typedef struct  node{    char element[10];    struct node * left;    struct node * right;    }node;typedef struct stack{    struct node * theNode;    struct stack * next;}stack;char * removeNewLineCharacter(char * string);char * removeSpaceCharacter(char * string);char * getInput(char * toDisplay);stack * push(stack *, node *);/*Create element to be put onto the stack.*/stack * pop(stack ** head);/*This function takes in the previous total, the first element and the second element to do the computation and the funcion as a character and returns the answer.*/stack * initElement(node * aNode);node * createNode(char * element);char * substring(char *string, int position, int length);int checkCharacter(char c);void parseEquation(char *);char * createSubstring(char *, int, int);int main(int argc, const char * argv[]) {    /*     Use scanf to get input from the user.     (((1 + 5.12) ∗ (2 − 7.68))/3)     */    char * theString;    theString = getInput("Please enter an equation : ");    parseEquation(theString);        return 0;}void parseEquation(char * theString){    int i = 0;    int a = 0;    stack * opperand = NULL;    stack * nonOpperand = NULL;    for(i = 0; i < strlen(theString); i ++)    {        char newString [5] = "";        a = 0;        if(isdigit(theString[i]) || isalpha(theString[i]) || theString[i] == '.')        {            while(isdigit(theString[i]) || isalpha(theString[i]) || theString[i] == '.')            {                newString[a] = theString[i];                i++;                a++;            }            newString[a] = '\0';            i = i - 1;        }                else if(checkCharacter(theString[i]))        {            newString[0] = theString[i];            newString[1] = '\0';        }        printf("%s",newString);        if(strcmp(newString,")") == 0)        {            printf(" pop\n");        }        else if(strcmp(newString, " ") == 0)        {            printf(" do nothing!\n");        }        else        {            if(checkCharacter(newString[0]) == -1)            {                /*opperand = push(opperand,createNode(newString));*/                printf(" push onto operand stack!\n");                createNode(newString);            }            else            {                printf(" push onto non-operand stack!\n");                createNode(newString);            }        }            }}int checkCharacter(char c){    if(c == ')' || c == '(' || c == '+' || c == '-' || c == '*' || c == '/' || c == ' ')    {        return -1;    }    return 0;}node * createNode(char * element){    node * toReturn = malloc(sizeof(node));    char * string = element;    printf("%s\n",string);    strcpy(toReturn -> element, string);    return toReturn;}char * removeNewLineCharacter(char * string){    int i;    char * newString = malloc(sizeof(string));        for(i = 0; i < strlen(string); i ++)    {        if(!strcmp(&string[i],"\n") == 0)        {            newString[i] = string[i];        }    }    return newString;}char * getInput(char * toDisplay){    char * toReturn;    char * theString;        theString = malloc(sizeof(char) * 100);    printf("%s",toDisplay);    fgets(theString,100,stdin);    toReturn = removeNewLineCharacter(theString);        free(theString);    return toReturn;    }stack * push(stack * head, node * element){    if(head == NULL)    {        head = initElement(element);        return head;    }        if(head != NULL)    {        stack * toAdd = initElement(element);        toAdd->next = head;        head = toAdd;        return head;            }    return head;}stack * pop(stack ** head){    stack * toPop = (*head);    (*head) = toPop->next;    toPop->next = NULL;    return toPop;}stack * initElement(node * element){    stack * toReturn;    toReturn = malloc(sizeof(stack));    toReturn->theNode = element;    return toReturn;}