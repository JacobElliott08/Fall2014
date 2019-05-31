
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define STRING_MAX_SIZE 30
#define NUM_STRINGS 20
int calculateKey(char * string);
char * createSubstring(char * theString, int start, int end);
int downHeap(int location, char ** heap);
int downHeapTwo(int location, char ** heap);

int main(int argc, const char * argv[]) {
    FILE * f;
    f = fopen("f.dat","r");
    char line [100];
    char * array[STRING_MAX_SIZE];
    int i = 0;
    if(f == NULL)
    {
        printf("File error please name the file f.dat\n");
        return 0;
    }
    if(f != NULL)
    {
        
        while(fgets(line,100,f) != NULL)
        {
            array[i] = strdup(line);
            i++;
        }
    }
    fclose(f);
    for(i = 0; i < NUM_STRINGS; i ++)
    {
        printf("%d  ,   %s",calculateKey(array[i]),array[i]);
    }
    printf("\n");
    /* while(downHeap(1, array) != 0){}; */
    int a = 1;
    for(a = 1; a < 21; a ++)
    {
        for(i = 1; i < 21; i ++)
        {
            downHeapTwo(i, array);
        }
    }
    
    
    for(i = 0; i < NUM_STRINGS; i ++)
    {
        printf("%d  ,   %s",calculateKey(array[i]),array[i]);
    }
    
    
    return 0;
}

int calculateKey(char * string)
{
    char * sNumOne = createSubstring(string, 0, 2);
    char * sNumTwo = createSubstring(string, 3, 5);
    char * sNumThree = createSubstring(string, 6, 8);
    int numOne = atoi(sNumOne);
    int numTwo = atoi(sNumTwo);
    int numThree = atoi(sNumThree);
    return numOne + numTwo + numThree;
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

/*
 public static void downHeap(int location){
 if(location < sizeOfTree){
 int p = location;
 int l = 2*p;
 int r = 2*p+1;
 int s = sizeOfTree;
 
 if(r<s && heap[r]<heap[p] && heap[r]<heap[l]){
 int temp = heap[r];
 heap[r] = heap[p];
 heap[p] = temp;
 downHeap(r);
 }else if(l<s && heap[l]<heap[p]){
 int temp = heap[l];
 heap[l] = heap[p];
 heap[p] = temp;
 downHeap(l);
 }
 }}
 */

int downHeapTwo(int location, char ** heap)
{
    if(location < NUM_STRINGS)
    {
        int realPosition = location - 1;
        int leftChild = (location * 2) -1;
        int rightChild = location * 2;
        int head = calculateKey(heap[realPosition]);
        int keyLeft = -1;
        int keyRight = -1;
        if(leftChild < NUM_STRINGS)
        {
            keyLeft = calculateKey(heap[leftChild]);
        }
        if(rightChild < NUM_STRINGS)
        {
            keyRight = calculateKey(heap[rightChild]);
        }
        /*printf("I AM COMPARING THESE VALUES : %d %d %d\n",head,keyLeft,keyRight);*/
        if(keyLeft != -1 && keyRight != -1)
        {
            if(keyRight > keyLeft)
            {
                if(head > keyLeft)
                {
                    /*printf("key Left smallest : ");
                    printf("%d %d %d\n",head,keyLeft,keyRight);*/
                    
                    char * temp = heap[realPosition];
                    heap[realPosition] = heap[leftChild];
                    heap[leftChild] = temp;
                    head = calculateKey(heap[realPosition]);
                    keyLeft = calculateKey(heap[leftChild]);
                    /*printf("Swap complete : %d %d %d\n",head,keyLeft,keyRight);
                    printf("WHAT : %d %d\n",leftChild,calculateKey(heap[leftChild]));*/
                    downHeapTwo(leftChild+1, heap);
                }
            }
            if(keyLeft > keyRight)
            {
                if(head > keyRight)
                {
                    
                    /*printf("key Right smallest : ");
                    printf("%d %d %d\n",head,keyLeft,keyRight);*/
                    char * temp = heap[realPosition];
                    heap[realPosition] = heap[rightChild];
                    heap[rightChild] = temp;
                    head = calculateKey(heap[realPosition]);
                    keyRight = calculateKey(heap[rightChild]);
                    /*printf("Swap complete : %d %d %d\n",head,keyLeft,keyRight);*/
                    downHeapTwo(rightChild+1, heap);
                    
                }
            }
        }
        else if(keyLeft != -1 && keyRight == -1)
        {
                if(head > keyLeft)
                {
                    /*printf("key Left smallest : ");
                    printf("%d %d %d\n",head,keyLeft,keyRight);*/
                    char * temp = heap[realPosition];
                    heap[realPosition] = heap[leftChild];
                    heap[leftChild] = temp;
                    head = calculateKey(heap[realPosition]);
                    keyLeft = calculateKey(heap[leftChild]);
                    /*printf("Swap complete : %d %d %d\n",head,keyLeft,keyRight);*/
                }
        }
    }
    
    return 0;
}

        /* Two cases where, the right child exists, but the left doesnt, or both exits*/
        /*
        if(leftChild < NUM_STRINGS && rightChild < NUM_STRINGS)
        {
            int head = calculateKey(heap[realPosition]);
            int keyLeft = calculateKey(heap[leftChild]);
            int keyRight = calculateKey(heap[rightChild]);
            
            if(keyRight < head && keyRight < keyLeft)
            {
                char * temp = heap[rightChild];
                heap[rightChild] = heap[realPosition];
                heap[realPosition] = temp;
                downHeap(rightChild+1, heap);
                
            }
            else if (keyLeft < head)
            {
                char * temp = heap[leftChild];
                heap[leftChild] = heap[realPosition];
                heap[realPosition] = temp;
                downHeap(leftChild+1, heap);
            }
        }
        else if(leftChild < NUM_STRINGS && rightChild >= NUM_STRINGS)
        {
            int head = calculateKey(heap[realPosition]);
            int keyLeft = calculateKey(heap[leftChild]);
            if(head > keyLeft)
            {
                char * temp = heap[leftChild];
                heap[leftChild] = heap[realPosition];
                heap[realPosition] = temp;
            }
        }*/



int downHeap(int location, char ** heap)
{
    int toReturn = 0;
    if(location < NUM_STRINGS)
    {
        int realPosition = location - 1;
        int leftChild = (location * 2) -1;
        int rightChild = location * 2;
        
        /* Two cases where, the right child exists, but the left doesnt, or both exits*/

        if(leftChild < NUM_STRINGS && rightChild < NUM_STRINGS)
        {
            int head = calculateKey(heap[realPosition]);
            int keyLeft = calculateKey(heap[leftChild]);
            int keyRight = calculateKey(heap[rightChild]);
            
            if(keyRight < head && keyRight < keyLeft)
            {
                /* right side is the smallest */
                char * temp = heap[rightChild];
                heap[rightChild] = heap[realPosition];
                heap[realPosition] = temp;
                toReturn = 1;
                downHeap(rightChild+1, heap);
                
            }
            else if (keyLeft < head)
            {
                char * temp = heap[leftChild];
                heap[leftChild] = heap[realPosition];
                heap[realPosition] = temp;
                toReturn = 1;
            }
            
            
            if(toReturn == 1)
            {
                downHeap(leftChild+1, heap);
                downHeap(rightChild+1, heap);
            }
            else
            {
                toReturn = downHeap(leftChild+1, heap);
                toReturn = downHeap(rightChild+1, heap);
            }
            
        }
        else if(leftChild < NUM_STRINGS && rightChild >= NUM_STRINGS)
        {
            int head = calculateKey(heap[realPosition]);
            int keyLeft = calculateKey(heap[leftChild]);
            if(head > keyLeft)
            {
                char * temp = heap[leftChild];
                heap[leftChild] = heap[realPosition];
                heap[realPosition] = temp;
                toReturn = 1;
            }
        }

    }
    return toReturn;
}
