#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct node
{
	char * key;
	int height;
	int occurance;
    struct node * left;
    struct node * right;

}node;
/*Creates a basic node with the key. Height set to 0 and Occurance set to 1*/
node * createNode(char * key);

/*Does the insertion into a BST using the AVL method of rotation of subtree*/
node * insert(node * head, char * key);

/*Returns the maximum between the two integers*/
int max(int one,int two);

/*Updates the heights of each node recursively*/
int updateHeight(node * head);

/*Determines the balance of the node head, and also update the heights recursivley*/
int determineBalance(node * head);

/*Prints the tree, used for debugging purposes*/
void inOrderPrintTree(node * head, int i);

/*Searches the tree for the key */
node * searchTree(node * head, char * key);


/*Rotations*/
/*Left rotation*/
node * leftRotation(node * theNode);

/*Right rotation*/
node * rightRotation(node * theNode);


/*Takes in a file 'fileName' and parses the file using the deliminator ' \n' and stores them in an array*/
char ** parseFile(char * fileName);

/*Gets the length of array of strings*/
int getLength(char * theString);

/*Used for the menu, check if the string can be casted to an integer and returns the integer */
int checkInput(char * string);

/*Used to check against Dr.Wangs output file*/
void  inOrderPrint(node * head);

/*Used to print all the nodes with the occurance above the value key*/
void printAbove(node * head, int key);

/*Returns the size of the BST*/
int getSize(node * head);

/*Deletes the node with the key*/
node * delete(node * head, char * key);

/*used in the delete method, when the swap occures with the next value in the inOrder traversal we need to delete the node
but the occurance got in the way, so we needed a deletion method that didn't take into account so we could delete cleanly*/
node * deleteDisregardOccurance(node * head, char * key);


/*Used to balance after insertion*/
node * balanceInsertion(node * head, char * key);


/*Used to balance after deletion*/
node * balanceDeletion(node * head);

int main(int argc, char const *argv[])
{
    node * head = NULL;
    char input[1000];
    /*1. Initialization
      2. Find
      3. Insert
      4. Remove
      5. Check Height and Size
      6. Find All (above a given frequency)
      7. Exit
    */
    while(strcmp(input,"7\n") != 0)
    {
    printf("1. Initialization\n");
    printf("2. Find\n");
    printf("3. Insert\n");
    printf("4. Remove\n");
    printf("5. Check Height and Size\n");
    printf("6. Find All (above a given frequency)\n");
    printf("7. Exit\n");
    printf("avl/>");
    fgets(input,1000,stdin);
        if(strcmp(input,"1\n") == 0) /*Initialization*/
        {
            char fileNameOne[100];
            char * fileName;
            char ** keys = NULL;
            int length;
            int i;

            printf("filename: ");
            
            fgets(fileNameOne,100,stdin);
            if(strcmp(fileNameOne,"\n") != 0)
            {
                /*Take the first token without \n*/
                fileName = strtok(fileNameOne,"\n");
                /*Create an array of strings for the file name*/
                keys = parseFile(fileName);
                /*Find the length of keys*/
                length = getLength(fileName);
                /*Insert each key into the AVL Tree*/
                for(i = 0; i < length; i ++)
                {
                    if(strcmp(keys[i],"\n") != 0)
                    {
                        head = insert(head,keys[i]);
                    }
                }

            }
        }
        if(strcmp(input,"2\n") == 0) /*Find*/
        {
            char keyy[100];
            char * key;
            printf("find key: ");
            fgets(keyy,100,stdin);
            if(strcmp(keyy,"\n")!= 0)
            {
                key = strtok(keyy,"\n");
                node * findKey = searchTree(head,key);
                if(findKey != NULL)
                {
                    printf("Key:%s frequency:%d\n",findKey->key,findKey->occurance);
                }
                else
                {
                    printf("no_such_key\n");
                }
            }

        }

        if(strcmp(input,"3\n") == 0) /*Insert*/
        {
            char keyy[100];
            char * key;
            printf("Insert key: ");
            fgets(keyy,100,stdin);
            if(strcmp(keyy,"\n")!= 0)
            {
                key = strtok(keyy,"\n");
                head = insert(head,key);
                node * findKey = searchTree(head,key);
                if(findKey != NULL)
                {
                    printf("Key:%s frequency:%d\n",findKey->key,findKey->occurance);
                }
            }
        }
        if(strcmp(input,"4\n") == 0) /*Remove*/
        {
            char keyy[100];
            char * key;
            printf("Delete key: ");
            fgets(keyy,100,stdin);
            if(strcmp(keyy,"\n")!= 0)
            {
                key = strtok(keyy,"\n");
                node * findKey = searchTree(head,key);
                if(findKey != NULL)
                {
                    head = delete(head,key);
                }
                else
                {
                    printf("no_such_key\n");
                }
            }
        }
        if(strcmp(input,"5\n") == 0) /*Check height and size*/
        {
            if(head != NULL)
            {
                printf("Height : %d Size : %d\n",(head->height-1),getSize(head));
            }
            else
            {
                printf("Height : 0 Size : 0\n");
            }
        }
        if(strcmp(input,"6\n") == 0)
        {
            char value[100];
            int selection = -1;
            do
            {
                printf("frequency : ");
                fgets(value,100,stdin);
            }while((selection = checkInput(value)) == -1);
            printAbove(head,selection);
        }
        if(strcmp(input,"8\n") == 0)
        {
            inOrderPrint(head);
        }  
        if(strcmp(input,"9\n") == 0)
        {
            inOrderPrintTree(head,0);
        }       
    }   
	return 0;
}


node * createNode(char * key)
{
	node * toReturn;
	toReturn = malloc(sizeof(node));
	toReturn -> key = malloc(sizeof(char) * (strlen(key) + 1));
	strcpy(toReturn->key,key);
	toReturn -> height = 1;
    toReturn -> occurance = 1;
	toReturn -> left = NULL;
	toReturn -> right = NULL;
	return toReturn;
}


node * insert(node * head, char * key)
{
	if(head == NULL)
    {
        head = createNode(key);
        return head;
    }
    /*Find the position in which the key should be placed.*/
    if(strcmp(key,head->key) > 0)
    {
        /*Re-creating eachsubtree as we go inserting */
        head->left = insert(head->left,key);
    }
    else if (strcmp(key,head->key) < 0)
    {
        /*Re-creating eachsubtree as we go inserting */
        head->right = insert(head->right,key);
    }
    /*If the key and the head are the same, we can just increase the occurance of the key*/
    else if (strcmp(key,head->key) == 0)
    {
    	head->occurance ++;
        return head;
    }

    /*After we insert the node we have to update the height of every node before the insertion*/
    head->height = updateHeight(head);
    /*We also have to determine the balance of each node to make sure nothing is out of balance*/
  
    head = balanceInsertion(head,key);

    return head;
}

node * balanceInsertion(node * head, char * key)
{
    int balance = determineBalance(head);

    /*This is when we do all the rotations if the balance is out of place*/
    if(balance < -1 &&(strcmp(key,head->right->key) < 0))
    {
        /*Left Rotation*/
        head = leftRotation(head);
    }
    else if (balance > 1 && (strcmp(key,head->left->key) > 0) )
    {
        /*Right rotation*/
        head = rightRotation(head);
    }
    else if(balance < -1 &&(strcmp(key,head->right->key) > 0))
    {
        /*Right left rotation*/
        head->right = rightRotation(head->right);
        head = leftRotation(head);
    }
    else if (balance > 1 && (strcmp(key,head->left->key) < 0))
    {
        /*Left right rotation*/
        head->left = leftRotation(head->left);
        head = rightRotation(head);
    }

    return head;
}

node * delete(node * head, char * key)
{
    
    /* a > b -1 if a < b 1*/
    if (head == NULL)
    {
        return head;
    }

    /*Find where we are supposed to delete the key*/
    if (strcmp(key,head->key) > 0)
    {
       head -> left =  delete(head->left, key);
    }
    else if(strcmp(key,head->key) < 0)
    {
        head -> right = delete(head->right, key);
    }
    else if(strcmp(head->key,key) == 0)
    {
        /*If the occurance can just be decremented by 1 then we do so*/
        if(head->occurance > 1)
        {
            head->occurance --;
            printf("Key :%s Frequency :%d\n",head->key,head->occurance);
        }
        else
        {
            if(head->left == NULL && head->right != NULL)
            {
                node * temp = head->right;
                head = temp;
            }
            else if (head -> right == NULL && head->left != NULL)
            {
                node * temp = head->left;
                head = temp;
            }
            else if (head ->right == NULL && head -> left == NULL)
            {
                head = NULL;
            }
            else if(head -> right != NULL && head -> left != NULL)
            {
                /*Find the next in order node to replace the node*/
                /*We go right once, and then go left as far as we can go*/
                node * x = head->right;
                while(x->left != NULL)
                {
                    x = x->left;
                }
                /*The easiest way to swap and delete the keys is just to copy the inorder's information to
                the to delete node, once we do that we can just delete in order node but we have to use a different
                function otherwise*/
                head->key = x->key;
                head->occurance = x->occurance;
                /*We want to delete the next in order node, which we find in the right subtree of the node*/
                head->right = deleteDisregardOccurance(head->right,x->key);
            }

        }
    }
    if(head != NULL)
    {
        /*Update the heights after deletions*/
        head->height = updateHeight(head);
        /*re-Balance the tree*/
        head = balanceDeletion(head);
    }
    return head;
}

node * balanceDeletion(node * head)
{
    int balance = determineBalance(head);
    
    if (balance > 1 && determineBalance(head->left) >= 0)
    {
        head = rightRotation(head);
        balance = determineBalance(head);
    }  

    else if (balance < -1 && determineBalance(head->right) <= 0)
    {
        head = leftRotation(head);
        balance = determineBalance(head);
    }  
 
     else if (balance < -1 && determineBalance(head->right) > 0)
    {
        head->right = rightRotation(head->right);
        head = leftRotation(head);
        balance = determineBalance(head);
    }

    else if (balance > 1 && determineBalance(head->left) < 0)
    {
        head->left =  leftRotation(head->left);
        head = rightRotation(head);
        balance = determineBalance(head);
    }  
 
    return head;
}

node * deleteDisregardOccurance(node * head, char * key)
{
    if (head == NULL)
    {
        return head;
    }
    if (strcmp(key,head->key) > 0)
    {
       head -> left =  deleteDisregardOccurance(head->left, key);
    }
    else if(strcmp(key,head->key) < 0)
    {
        head -> right = deleteDisregardOccurance(head->right, key);
    }
    else if(strcmp(head->key,key) == 0)
    {
        if(head->left == NULL && head->right != NULL)
        {
            node * temp = head->right;
            head = temp;
        }
        else if (head -> right == NULL && head->left != NULL)
        {
            node * temp = head->left;
            head = temp;
        }
        else if (head ->right == NULL && head -> left == NULL)
        {
            head = NULL;
        }
    }
    if(head != NULL)
    {

        head->height = updateHeight(head);
    }
    return head;
}


/*Parse the file into an array*/
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
                keys = realloc(keys,(sizeof(char *) * (count+1)));
                keys[count] = malloc(strlen(token)+1);
                strcpy(keys[count], token);
                count ++;
                token = strtok(NULL," \n");
            }
        }
    }
    fclose(f);
    return keys;
}







/***********************************/
/*        UTILITY functions        */
/***********************************/
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
    {
        return 0;
    }
    return max(updateHeight(head->left), updateHeight(head->right))+1;
}
int determineBalance(node * head)
{
    return updateHeight(head->left) - updateHeight(head->right);
}
int getSize(node * head)
{
    int value = 0;
    if(head == NULL)
    {
        return 0;
    }
    value ++;
    value += getSize(head->left);
    value += getSize(head->right);
    return value;
}
    /**************************/
    /*String utility functions*/
    /**************************/
int getLength(char * fileName)
{
    FILE * f;
    f = fopen(fileName,"r");

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
                count ++;
                token = strtok(NULL," \n");
            }
        }
    }
    fclose(f);

    return count;
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
        if(!isdigit(string[i]) && !(string[i] == '\0') && !(string[i] == '\n'))
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




/***********************************/
/*           Rotations             */
/***********************************/
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

/***********************************/
/*          PRINTING functions     */
/***********************************/
void  inOrderPrint(node * head)
{
    if (head == NULL)
    {
        return;
    }
    inOrderPrint(head->right);
    printf("%s %d %d\n",head->key,head->occurance,head->height);
    inOrderPrint(head->left);
}

void printAbove(node * head, int frequency)
{
    if (head == NULL)
    {
        return;
    }
    printAbove(head->right,frequency);
    if(head->occurance > frequency)
    {
        printf("key: %s, frequency: %d\n",head->key,head->occurance);
    }
    printAbove(head->left,frequency);
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
    printf("%s %d %d\n",head->key,head->height,determineBalance(head));
    /*printf("%s\n",head->key);*/
    inOrderPrintTree(head->left,i+1);

}


/***********************************/
/*SEARCH FUNCTION*/
/***********************************/
node * searchTree(node * head,char * information)
{
    node * result = NULL;
    if(head == NULL)
    {
        return NULL;
    }
    if(head->left != NULL)
    {
        result = searchTree(head->left,information);
    }
    
    if(strcmp(head->key,information) == 0)
    {
        return head;
    }
    
    if(result == NULL && head->right != NULL)
    {
        result = searchTree(head->right, information);
    }
    
    return result;
}















