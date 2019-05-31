#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

typedef struct node
{
    char * element;
    struct node * left;
    struct node * right;
    
}node;

typedef struct stack
{
    struct node * theNode;
    struct stack * next;
}stack;

char * createSingleString(char * theString, int start);
char * createSubstring(char *, int start, int end);
node * searchTree(node * head,char * information);
char * removeNewLineCharacter(char * string);
stack * push(stack * head, node * element);
void inOrderPrintTree(node * head, int i);
stack * initElement(node * element);

node * createNode(char * element);
char * getInput(char * toDisplay);
void postOrderPrint(node * head);
void preOrderPrint(node * head);
void inOrderPrint(node * head);

stack * constructBinaryTree();
int checkInput(char * string);
void cleanTree(node * tree);
int checkCharacter(char c);
stack * pop(stack ** head);
double solveTree(node * tree);
double doCalculation(double left, char * operand, double right);


void someMethod(node * tree);


int main()
{
    node * tree = (constructBinaryTree())->theNode;
    

    
    
    /* 1. Display 2. Preorder 3. Inorder 4. Postorder 5. Update 6. Calculate 7. Exit */
    /*printf("\nPost Order Print : \n");
    postOrderPrint(merp->theNode);
    printf("\nPre Order Print : \n");
    preOrderPrint(merp->theNode);*/
    /*printf("\nIn Order Print\n");
    inOrderPrint(tree);*/
    /*printf("WHAT IS IN MERP : %s%s%s\n",merp->left->element,merp->element,merp->right->element);*/
    /*printf("\nIn Order Print Tree\n");
    inOrderPrintTree(tree,0);*/
    someMethod(tree);
    /* search for A1 */
    
    /*char * stuff = "a1";
    node * element = searchTree(tree, stuff);*/
    
    /*printf("TEST : %s",element->element);
    strcpy(element->element, "0.00");
    inOrderPrintTree(tree, 0);*/
    /*(((x1 + 5.12) ∗ (x2 − 7.68))/x3)*/
    
    
    
    return 0;
}

void someMethod(node * theTree)
{
    node * tree = theTree;
    /*printf("Whats in the tree : %s%s%s\n",tree->left->element,tree->element,tree->right->element);*/
    int selection = -1;
    char input[100];
    do
    {
        do
        {
            printf("1. Display\n");
            printf("2. Preorder\n");
            printf("3. Inorder\n");
            printf("4. Postorder\n");
            printf("5. Update\n");
            printf("6. Calculate\n");
            printf("7. Exit\n");
            fgets(input,100,stdin);
            
        } while ((selection = checkInput(input)) == -1);
        
        switch(selection)
        {
            case 1:
                printf("VIEW OF THE TREE\n");
                inOrderPrintTree(tree,0);
                break;
            case 2:
                printf("PREORDER VIEW OF THE TREE\n");
                preOrderPrint(tree);
                printf("\n");
                break;
            case 3:
                printf("INORDER VIEW OF THE TREE\n");
                inOrderPrint(tree);
                printf("\n");
                break;
            case 4:
                printf("POSTORDER VIEW OF THE TREE\n");
                postOrderPrint(tree);
                printf("\n");
                break;
            case 5:
                {
                char * replacementString = getInput("Please enter the variable name, and the new value : (Variable name, new Value) : ");
                    char * tokenOne = strtok(replacementString,",\n");
                    char * tokenTwo = strtok(NULL,",\n");
                    if(tokenOne == NULL || tokenTwo == NULL)
                    {
                        printf("Cannot replace null characters!\n");
                        break;
                    }
                    node * toChange = searchTree(theTree, tokenOne);
                    strcpy(toChange->element,tokenTwo);
                }
                break;
            case 6:
                printf("6 was slected\n");
                {
                    double total;
                    total = solveTree(tree);
                    printf("The total is : %0.2lf\n",total);
                }
                break;
            case 7:
                printf("Good bye.\n");
                cleanTree(tree);
                if(tree == NULL)
                {
                }
                break;
        }
    } while (selection != 7);
    
}
/*
 private int evaluate(Node n)
 {
 if (n != null)
 {
 if (n.isLeaf())  // n is a node with a number
 return Integer.parseInt(n.element);
 else
 {
 int left = evaluate(n.left);
 int right = evaluate(n.right);
 return calculate(left, n.element, right);
 } //end else
 } //end if
 return 0;
 } //end evaluate
 */
double solveTree(node * tree)
{
    if(tree != NULL)
    {
        if(tree->left == NULL && tree->right == NULL)
        {
            return atof(tree->element);
        }
        else
        {
            double left = solveTree(tree->left);
            double right = solveTree(tree->right);
            return doCalculation(left,tree->element,right);
        }
    }
    return 0.00;
}

double doCalculation(double left, char * operand, double right)
{
    if(strcmp("+",operand) == 0)
    {
        return left + right;
    }
    if(strcmp("-",operand) == 0)
    {
        return left - right;
    }
    if(strcmp("*",operand) == 0)
    {
        return left * right;
    }
    if(strcmp("/",operand) == 0)
    {
        return left / right;
    }
    return 0.00;
}
void cleanTree(node * tree)
{
    if(tree != NULL)
    {
        cleanTree(tree->left);
        cleanTree(tree->right);
        tree->left = NULL;
        tree->right = NULL;
        free(tree->element);
        free(tree);
    }
}
int checkInput(char * string)
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
/*
 
 TreeNode result = null;
 if(cur.left != null)
 result = find(cur.left,val);
 
 
 if(cur.value == val)
 return cur;
 if(result ==null && cur.right != null)
 result = find(cur.right,val);
 
 return result;
 */
node * searchTree(node * head,char * information)
{
    node * result = NULL;
    
    if(head->left != NULL)
    {
        result = searchTree(head->left,information);
    }
    
    if(strcmp(head->element,information) == 0)
    {
        return head;
    }
    
    if(result == NULL && head->right != NULL)
    {
        result = searchTree(head->right, information);
    }
    
    return result;
}

void inOrderPrintTree(node * head, int i)
{
    int a = 0;
    if(head == NULL)
    {
        return;
    }
    inOrderPrintTree(head->left,i+1);
    for(a = 0; a < i; a ++)
    {
        printf("\t");
    }
    printf("%s\n",head->element);
    if(head->right != NULL)
    {
        inOrderPrintTree(head->right,i+1);
    }
}

void inOrderPrint(node * head)
{
    if(head == NULL)
    {
        return;
    }
    if(checkCharacter(head->element[0]) == -1)
    {
        printf("( ");
    }
    inOrderPrint(head->left);
    printf(" %s ",head->element);
    inOrderPrint(head->right);
    if(checkCharacter(head->element[0]) == -1)
    {
        printf(" )");
    }
    
}
void postOrderPrint(node * head)
{
    if(head == NULL)
    {
        return;
    }

    postOrderPrint(head->left);
    postOrderPrint(head->right);
    printf(" %s ",head->element);
}

void preOrderPrint(node * head)
{
    if(head == NULL)
    {
        return;
    }
    printf(" %s ",head->element);
    preOrderPrint(head->left);
    preOrderPrint(head->right);
}
stack * constructBinaryTree()
{
    int i = 0;
    int stop = 0;
    int start = 0;
    char input[100];
    char * newString = NULL;
    printf("Enter an equation : ");
    
    stack * opperand = NULL;
    stack * nonOpperand = NULL;
    
    fgets(input,100,stdin);
    
    
    for(i = 0; i < strlen(input); i ++)
    {
        if(isspace(input[i]))
        {
            continue;
        }
        if(isdigit(input[i]) || isalpha(input[i]) || input[i] == '.')
        {
            start = i;
            stop = i;
            while(isdigit(input[stop]) || isalpha(input[stop]) || input[stop] == '.')
            {
                stop ++;
            }
            i = stop-1;
            newString = createSubstring(input, start, stop);
        }
        if(checkCharacter(input[i]))
        {
            newString = createSingleString(input, i);
        }
        /*printf("THE STRING CREATED : %s\n",newString);*/
        if(strcmp(newString,")") == 0)
        {
            /*printf(" pop\n");*/
            node * opperandOne = pop(&nonOpperand)->theNode;
            node * opperandTwo = pop(&nonOpperand)->theNode;
            node * actualOpperand = pop(&opperand)->theNode;
          /*  printf("%s Was popped\n",opperandOne->element);
            printf("%s Was popped\n",opperandTwo->element);
            printf("%s Was popped\n",actualOpperand->element);*/
            actualOpperand->left = opperandTwo;
            actualOpperand->right = opperandOne;
           /* printf("The new node is now : %s %s %s\n",actualOpperand->left->element,actualOpperand->element,actualOpperand->right->element);*/
            nonOpperand = push(nonOpperand, actualOpperand);
        }
        else if(strcmp(newString, " ") == 0)
        {
            /*printf(" do nothing!\n");*/
        }
        else if(strcmp(newString,"(") == 0)
        {
            /*printf(" do nothing!\n");*/
        }
        else
        {
            if(checkCharacter(newString[0]) == -1)
            {
                /*printf(" push onto operand stack!\n");*/
                node * newNode = createNode(newString);
                opperand = push(opperand,newNode);
            }
            else
            {
                /*printf(" push onto non-operand stack!\n");*/
                node * newNode = createNode(newString);
                nonOpperand = push(nonOpperand,newNode);
                
            }
        }
    }
   /* printf("WHAT IS BEING RETURNED : %s %s %s\n",nonOpperand->theNode->left->element, nonOpperand->theNode->element, nonOpperand->theNode->right->element);*/
    return nonOpperand;
}


char * createSubstring(char * theString, int start, int end)
{
    int i;
    char * newString = malloc(end-start+1);
    for(i = start; i < end; i ++)
    {
        newString[i-start] = theString[i];
    }
    return newString;
}

char * createSingleString(char * theString, int start)
{
    char * newString = malloc(sizeof(char) * 2);
    newString[0] = theString[start];
    newString[1] = '\0';
    return newString;
}

char * removeNewLineCharacter(char * string)
{
    int i;
    char * newString = malloc(sizeof(string));
    
    for(i = 0; i < strlen(string); i ++)
    {
        if(string[i] == '\n')
        {
            newString[i] = '\0';
        }
        else
        {
            newString[i] = string[i];
        }
    }
    return newString;
}

int checkCharacter(char c)
{
    if(c == ')' || c == '(' || c == '+' || c == '-' || c == '*' || c == '/')
    {
        return -1;
    }
    return 0;
}

node * createNode(char * element)
{
    node * toReturn;
    toReturn = malloc(sizeof(node));
    toReturn->element = malloc(strlen(element) + 1);
    toReturn->left = NULL;
    toReturn->right = NULL;
    strcpy(toReturn->element,element);
    return toReturn;
}

stack * push(stack * head, node * element)
{
    if(head == NULL)
    {
        head = initElement(element);
        return head;
    }
    
    if(head != NULL)
    {
        stack * toAdd = initElement(element);
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

stack * initElement(node * element)
{
    stack * toReturn;
    toReturn = malloc(sizeof(stack));
    toReturn->theNode = element;
    return toReturn;
}

char * getInput(char * toDisplay)
{
    char * theString;
    theString = malloc(sizeof(char) * 100);
    printf("%s",toDisplay);
    fgets(theString,100,stdin);
    return theString;
    
}