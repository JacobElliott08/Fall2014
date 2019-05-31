/*
 a) Left Left Case
 
    T1, T2, T3 and T4 are subtrees.
        z                                      y
       / \                                   /   \
      y   T4      Right Rotate (z)          x      z
     / \          - - - - - - - - ->      /  \    /  \
    x   T3                               T1  T2  T3  T4
   / \
 T1   T2
    z height = 2 (left 3 - right 1)
    x < y
    therefore single right rotation

 
node * rightRotation(node * theNode)
{
    node * z = theNode;
    node * y = z->left;
    node * T3 = y->right;
    
    z->left = T3;
    y->right = z;
    
    z->height = max(z->left->height,z->right->height)+1;
    y->height = max(y->left->height,y->right->height)+1;
 
    return y;
}
 
 b) Left Right Case
 
            z                               z                           x
           / \                             / \                         / \
          y   T4  Left Rotate (y)         x   T4  Right Rotate(z)     y   z
         / \      - - - - - - - - ->     / \      - - - - - - - ->   / \ / \
        T1  x                           y   T3                      T1 T2T3  T4
           / \                         / \
         T2   T3                      T1  T2
 
    z height = 2 (unbalanced)
    x > y 
    therefore left right rotation
    
 node * LeftRightRotation(node * theNode)
 {
    theNode->left =  leftRotate(theNode->left);
    theNode = rightRotate(theNode);
 
    return theNode;
 }
 
 
 c) Right Right Case
 
            z                                y
           / \                              / \
          T1  y     Left Rotate(z)         z   x
             / \   - - - - - - - ->       / \ / \
            T2  x                        T1 T2T3 T4
               / \
              T3  T4
        z height =  -2 (left 1 - right 3)
        x > y   
        therfore single left rotation
 
node * leftRotation(node * theNode)
{
    node * z = theNode;
    node * y = z->right;
    node * x = y->right;
    node * T2 = y->right;
 
    y->right = z;
    z->left = T2;
 
    y->height = max(y->left->height,y->right-height)+1;
    z->height = max(z->left->height,z->right->height)+1;
 
    return y;
 
}
 
 
 d) Right Left Case
 
        z                            z                            x
       / \                          / \                          /  \
     T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      x
         / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
        x   T4                      T2   y                  T1  T2  T3  T4
       / \                              /  \
     T2  T3                           T3   T4
    
    z height = -2 (left 1 - right 3)
    x < y
    therefore right left rotation

node * RightLeftRotation(node * theNode)
{
    node->right = rightRotate(node->right);
    return leftRotate(node);
}
*/


#include<stdio.h>
#include<stdlib.h>
#include <string.h>
// An AVL tree node
typedef struct node
{
    int key;
    char * element;
    struct node *left;
    struct node *right;
    int height;
    int occurence;
} node;

// A utility function to get maximum of two integers
int max(int a, int b);
int height(node * theNode);

node* createNode(int key, char * element);

node * insert(node *, int key, char * element);



void inOrderPrintTree(node * head, int i);
int determineHeight(node * head);
node * leftRotation(node * theNode);
node * rightRotation(node * theNode);
node * initTree(FILE * file);
char * createSubstring(char *, int);

int determineBalance(node * head);

/*This function is neccessary incase eaither the left or right node is null*/
int height(node * theNode)
{
    if (theNode == NULL)
    {
        return 0;
    }
    return theNode->height;
}



// A utility function to get maximum of two integers
int max(int a, int b)
{
    if(a > b)
    {
        return a;
    }
    else
    {
        return b;
    }
}


/**
        z                                 y
       / \                              /   \
      T1  y     Left Rotate(z)         z     x
         / \   - - - - - - - ->       / \   / \
        T2  x                        T1 T2 T3 T4
           / \
          T3  T4
 */
node * leftRotation(node * theNode)
{
    node * z = theNode;
    node * y = z->right;
    node * T2 = y->left;
    
    y->left = z;
    z->right = T2;
    
    y->height = max(height(y->left),height(y->right))+1;
    z->height = max(height(z->left),height(z->right))+1;
    
    return y;
    
}

node * rightRotation(node * theNode)
{
    node * z = theNode;
    node * y = z->left;
    node * T3 = y->right;
    
    z->left = T3;
    y->right = z;
    
    z->height = max(height(z->left),height(z->right))+1;
    y->height = max(height(y->left),height(y->right))+1;
    
    return y;
}

/* Helper function that allocates a new node with the given key and
 NULL left and right pointers. */
node* createNode(int key, char * element)
{
    
    node * toReturn =  malloc(sizeof(struct node));
    toReturn->key   = key;
    toReturn->left   = NULL;
    toReturn->right  = NULL;
    toReturn->height = 1;
    toReturn->occurence = 1;
    printf("in here\n");
    toReturn->element = malloc(strlen(element+1));
    strcpy(toReturn->element, element);
    printf("in here1\n");
    return toReturn;
}







node * insert(node * head, int key, char * element)
{
    if(head == NULL)
    {
        head = createNode(key,element);
        return head;
    }
    if(key < head->key)
    {
        head->left = insert(head->left, key,element);
    }
    else if (key > head->key)
    {
        head->right = insert(head->right,key,element);
    }
    else if (key == head->key)
    {
        if(strcmp(element,head->element) == 0)
        {
            printf("Element : %s has a duplicate.\n",element);
            head->occurence++;
            return head;
        }
    }
    
    head->height = determineHeight(head);
    int balance = determineBalance(head);
    
    
    printf("Balance : %d\n",balance);
    
   if(balance > 1 && key < head->left->key)
    {
        return rightRotation(head);
    }
    
    if(balance > 1 && key > head->left->key)
    {
        head->left = leftRotation(head->left);
        return rightRotation(head);
    }
    
    if(balance < -1 && key > head->right->key)
    {
        return leftRotation(head);
    }
    
    if(balance < -1 && key < head->right->key)
    {
        head->right = rightRotation(head->right);
        return leftRotation(head);
    }
    return head;
}

int determineBalance(node * head)
{
    node * left = head->left;
    node * right = head->right;
    
    int leftHeight = 0;
    int rightHeight = 0;
    
    if(left != NULL)
    {
        leftHeight = left->height;
    }
    
    if(right != NULL)
    {
        rightHeight = right->height;
    }
    
    return leftHeight-rightHeight;
}


/* Given a non-empty binary search tree, return the node with minimum
 key value found in that tree. Note that the entire tree does not
 need to be searched. */
struct node * minValueNode(struct node* node)
{
    struct node* current = node;
    
    /* loop down to find the leftmost leaf */
    while (current->left != NULL)
    current = current->left;
    
    return current;
}



// A utility function to print preorder traversal of the tree.
// The function also prints height of every node
void preOrder(struct node *root)
{
    if(root != NULL)
    {
        printf("%d ", root->key);
        preOrder(root->left);
        preOrder(root->right);
    }
}

/* Drier program to test above function*/

node * initTree(FILE * file)
{
    FILE * f;
    f = fopen("data.txt","r");
    char inLine[128];
    char * token;
    char * actualToken;
    
    node * toReturn = NULL;
    
    if(f != NULL)
    {
        while(fgets(inLine, 128, f) != NULL)
        {
            token = strtok(inLine," ,\n");
            while(token != NULL)
            {
                printf("token : %s\n",token);
                actualToken = createSubstring(token, 3);
                printf("Actual token : %d\n",atoi(actualToken));
                
                toReturn = insert(toReturn, atoi(actualToken), token);
                
                
                token = strtok(NULL," ,\n");
            }
        }
    }
    return toReturn;
}

int main()
{
    
    
    
    
    struct node *root = NULL;
    
    /* Constructing tree given in the above figure */
    root = initTree(NULL);

    
    /* The constructed AVL Tree would be
                9
              /  \
             1    10
           /   \     \
          0    5     11
        /    /  \
       -1   2    6
     */
    
    printf("Pre order traversal of the constructed AVL tree is \n");
    inOrderPrintTree(root, 0);
    
    /* The AVL Tree after deletion of 10
     1
     /  \
     0    9
     /     /  \
     -1    5     11
     /  \
     2    6
     */
    

    
    return 0;
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
    printf("%d\n",head->key);
    if(head->right != NULL)
    {
        inOrderPrintTree(head->left,i+1);
    }
}


int determineHeight(node * head)
{
    if(head == NULL)
    {
        return 1;
    }
    if(head->left == NULL)
    {
       if(head->right != NULL)
        {
            return head->right->height+1;
        }
        if(head->right == NULL)
        {
            return 1;
        }
    }
    if(head->left != NULL)
    {
        if(head->right == NULL)
        {
            return head->left->height + 1;
        }
        else
        {
            if(head->left->height > head->right->height)
            {
                return head->left->height+1;
            }
            else
            {
                return head->right->height+1;
            }
        }
    }
    return 1;
}
char * createSubstring(char * string, int start)
{
    int length = ((int)strlen(string) - start);
    char * toReturn;
    toReturn = malloc(length + 2);
    int i = 0;
    for(i = 0; i < length; i ++)
    {
        toReturn[i] = string[i + start];
    }
    toReturn[strlen(toReturn)] = '\0';
    return toReturn;
}















