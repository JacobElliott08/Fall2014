#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct node
{

    int height;
    int occurance;
    char * key;
    
    struct node * left;
    struct node * right;
    
}node;

node * createNode(char * realKey);
node * insert(node * head, char * element);
node * rightRotation(node * theNode);
node * leftRotation(node * theNode);
node * createTree();
int getKey(node * head);

/*String parsing functions*/
int getLength(char **);
char * createSubstring(char * source, int start);
char ** parseFile(char * fileName);

/*Some utility functions*/
int max(int one,int two);
int updateHeight(node * head);
int determineBalance(node * head);
void inOrderPrintTree(node * head, int i);


int main(int argc, const char * argv[])
{
    node * tree = createTree();
    if(tree == NULL)
    {
        return 1;
    }
}



node * createNode(char * key)
{
    node * toReturn = malloc(sizeof(node));

    toReturn -> height = 0;
    toReturn -> occurance = 1;
    
    toReturn -> key = malloc(strlen(key) + 1);
    strcpy(toReturn -> key, key);
    
    toReturn -> left = NULL;
    toReturn -> right = NULL;
    
    return toReturn;
}



node * createTree()
{
    node * toReturn = NULL;
    char ** keys;
    keys = parseFile("data1.txt");
    int total = getLength(keys);
    int i;
    for(i = 0; i < total; i ++)
    {
        char merp [10];
        char * dest;
        dest = createSubstring(keys[i], 3);
        printf("%s : %s\n",dest,keys[i]);
        toReturn = insert(toReturn, keys[i]);
        printf("\nThe tree : \n");
        
        inOrderPrintTree(toReturn, 0);
        
        fgets(merp,10,stdin);
        
    }
    return toReturn;
}

node * insert(node * head, char * key)
{
    if(head == NULL)
    {
        head = createNode(key);
        return head;
    }
    if(strcmp(key,head->key) < 0)
    {
        head->left = insert(head->left, key);
    }
    else if (strcmp(head->key,key) > 0)
    {
        head->right = insert(head->right,key);
    }
    else if (strcmp(head->key,key) == 0)
    {

    }
    
    head->height = updateHeight(head);
    int balance = determineBalance(head);
    
    if(balance > 1 && key < head->left->key)
    {
        head = rightRotation(head);
    }
    
    if(balance > 1 && key > head->left->key)
    {
        head->left = leftRotation(head->left);
        head = rightRotation(head);
    }
    
    if(balance < -1 && key > head->right->key)
    {
        head = leftRotation(head);
    }
    
    if(balance < -1 && key < head->right->key)
    {
        head->right = rightRotation(head->right);
        head = leftRotation(head);
    }
    return head;
}
int getKey(node * head)
{
    if(head == NULL)
    {
        return 0;
    }
    return head->key;
}

/**********************************************************************************/
/*String parsing functions*/
/**********************************************************************************/


char ** parseFile(char * fileName)
{
    FILE * f;
    f = fopen(fileName,"r");
    
    char ** keys = NULL;
    int count = 0;
    
    if(f != NULL)
    {
        char line[10000];
        count = 0;
        while(fgets(line, 10000, f) != NULL)
        {
            char * token;
            token = strtok(line," \n");
            while(token != NULL)
            {
                int arraySize = (count+1)*sizeof(char*);
                keys = realloc(keys,arraySize);
                
                if(keys == NULL)
                {
                    printf("Realloc unsuccessful\n");
                    exit(EXIT_FAILURE);
                }
                int stringSize = strlen(token)+1;
                keys[count] = malloc(stringSize);
                if(keys[count]==NULL)
                {
                    printf("Malloc unsuccessful\n");
                    exit(EXIT_FAILURE);
                }
                strcpy(keys[count], token);
                count ++;
                token = strtok(NULL," \n");
            }
        }
    }
    fclose(f);
    return keys;
}
char * createSubstring(char * source, int start)
{
    int i;
    char * toReturn;
    toReturn = malloc(sizeof(char) * ((strlen(source) - start)) +1);
    if(toReturn == NULL)
    {
        printf("Malloc error\n");
        exit(0);
    }
    for(i = start; i < strlen(source); i ++)
    {
        toReturn[i-start] = source[i];
    }
    toReturn[strlen(toReturn) + 1] = '\0';
    return toReturn;
}

int getLength(char ** theString)
{
    int i = 0;
    while (*theString != NULL) {
        i++;
        (theString)++;
    }
    return i;
}


/**********************************************************************************/
/*Common utility functions*/
/**********************************************************************************/
int max(int one, int two)
{
    if(one > two)
    {
        return one;
    }
    return two;
}

int updateHeight(node * head)
{
    if (head == NULL)
    return 0;
    else
    return max(updateHeight(head->left), updateHeight(head->right))+1;
}

int determineBalance(node * head)
{
    node * left = head->left;
    node * right = head->right;
    
    int leftHeight = 0;
    int rightHeight = 0;
    
    if(left != NULL)
    {
        left->height = updateHeight(left);
        leftHeight = left->height;
    }
    
    if(right != NULL)
    {
        right->height = updateHeight(right);
        rightHeight = right->height;
    }
    return leftHeight-rightHeight;
}

void inOrderPrintTree(node * head, int i)
{
    int a = 0;
    if(head == NULL)
    {
        return;
    }
    inOrderPrintTree(head->right,i+1);
    for(a = 0; a < i; a ++)
    {
        printf("\t");
    }
    printf(" %d %d ",head->height,determineBalance(head));
    printf("%d\n",head->key);
    inOrderPrintTree(head->left,i+1);
}


/**********************************************************************************/
/*Rotation Functions*/
/**********************************************************************************/

node * leftRotation(node * theNode)
{
    node * x = theNode;
    node * y = x->right;
    node * T2 = y->left;
    
    y->left = x;
    x->right = T2;
    
    
    x->height = updateHeight(x);
    y->height = updateHeight(y);
    return y;
}

node * rightRotation(node * theNode)
{
    node * z = theNode;
    node * y = z->left;
    node * T3 = y->right;
    
    z->left = T3;
    y->right = z;
    
    
    z->height = updateHeight(z);
    y->height = updateHeight(y);
    
    return y;
}









