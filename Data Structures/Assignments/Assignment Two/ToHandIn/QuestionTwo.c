/*Jacob Elliott 0835468 Question Two Assignment Two*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct stack stack;
/*Basic push function returns the head of the stack takes in a stack and an integer.*/
stack * push(stack *, int);
/*Create element to be put onto the stack.*/
stack * initElement(int);
/*Prints the list.*/
stack * printList(stack * head);
/*Pops the first value of the head of the stack.*/
stack * pop(stack ** head);
/*This function takes in the previous total, the first element and the second element to do the computation and the funcion as a character and returns the answer.*/
double compute(double total, stack * elementOne, stack * elementTwo, char c);

struct stack
{
    double element;
    stack * next;
};

int main(int argc, char * argv[])
{
    if(argc != 2)
    {
        printf("Please enter a post fix function.");
        return 0;
    }
    char * string = argv[1];
    char element;
    int i;
    double toPush = 0;
    double total = 0;
    stack * head = NULL;
    
    
    for(i = 0; i < strlen(string); i ++)
    {
        
        if (isdigit(string[i]))
        {
            element = string[i];
            toPush = (double)atoi(&element);
            head = push(head,toPush);
        }
        if(!isdigit(string[i]))
        {
            stack * elementOne = pop(&head);
            stack * elementTwo = pop(&head);
            total = compute(total,elementOne,elementTwo,string[i]);
            head = push(head,total);
        }
    }
    
    printf("The final total is : %0.2f\n",head->element);
    return 0;
}

stack * printList(stack * head)
{
    stack * current;
    current = head;
    while(current != NULL)
    {
        printf("%02.f\n",current->element);
        current = current->next;
    }
    return head;
}
stack * push(stack * head, int toPush)
{
    if(head == NULL)
    {
        head = initElement(toPush);
        return head;
    }
    
    if(head != NULL)
    {
        stack * toAdd = initElement(toPush);
        toAdd->next = head;
        head = toAdd;
        return head;
        
    }
    return head;
}

stack * pop(stack ** head)
{
    stack * toPop = (*head);
    (*head) = toPop->next;
    toPop->next = NULL;
    return toPop;
}

stack * initElement(int element)
{
    stack * toReturn;
    toReturn = malloc(sizeof(stack));
    toReturn -> element = element;
    toReturn -> next = NULL;
    return toReturn;
}

double compute(double total, stack * elementOne, stack * elementTwo, char c)
{
    if(c == '+')
    {
        total =  elementTwo->element + elementOne->element;
    }
    else if(c == '-')
    {
        total = elementTwo->element - elementOne->element;
    }
    else if (c == '*')
    {
        total = elementTwo->element * elementOne->element;
    }
    else if(c == '/')
    {
       total = elementTwo->element / elementOne->element;
    }
    return total;
}
