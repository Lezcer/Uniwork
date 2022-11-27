#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"

LinkedList* createLinkedList()
{
    
    LinkedList* list;
    list = (LinkedList*)malloc(sizeof(LinkedList));

    list->head = NULL;
    list->tail = NULL;
    list->count = 0;
    return list;
}
int getCount(LinkedList* list)
{
    return list->count;
}
int isEmpty(LinkedList* list)
{
    int empty=FALSE;
    if(list->count == 0)
        empty=TRUE;
    return empty;
}
void insertStart(LinkedList* list, void* entry)
{
    LinkedListNode* node;
    node = (LinkedListNode*)malloc(sizeof(LinkedListNode));
    node->data = entry;
    
    if(list->count == 0)/*i.e. Empty*/
    {
        node->next = NULL;
        list->head = node;
        list->tail = node;
    }
    else
    /*i.e. List has more than 0 node*/
    {
        node->next = list->head;
        list->head = node;
    }
    list->count++;
}

void* removeStart(LinkedList* list)
{
    LinkedListNode* temp;
    if(list->count == 0)
        printf("Linked List is empty");
    else
    {
        temp = list->head;
        if(list->count == 1)/*i.e. List contains only one node*/
        {
            list->head = NULL;
            list->tail = NULL;
        }
        else/*i.e. List has more than 1 node*/
        {
            list->head = list->head->next;
            temp->next = NULL;
        }
        list->count--;
        /*Am I suppose to free the nodes???*/
    }
    return temp->data;
}

void insertLast(LinkedList* list, void* entry)
{
    LinkedListNode* node;
    node = (LinkedListNode*)malloc(sizeof(LinkedListNode));
    node->data = entry;
    
    if(list->count == 0)/*i.e. Empty*/
    {
        node->next = NULL;
        list->head = node;
        list->tail = node;
    }
    else
    /*i.e. List has more than 0 node*/
    {
        node->next = NULL;
        list->tail->next = node;
        list->tail = node;
    }
    list->count++;
}

void* removeLast(LinkedList* list)
{
    LinkedListNode *temp, *prevTemp;
    if(list->count == 0)
        printf("Linked List is empty");
    else
    {
        temp = list->head;
        if(list->count == 1)/*i.e. List contains only one node*/
        {
            list->head = NULL;
            list->tail = NULL;
        }
        else/*i.e. List has more than 1 node*/
        {
            while(temp->next != NULL)
            {
                prevTemp = temp;
                temp = temp->next;
            }
            prevTemp->next = NULL;
        }
        list->count--;
        /*Am I suppose to free the nodes???*/
    }
    return temp->data;
}


void printLinkedList(LinkedList* list, listFunc funcPtr)
{
    LinkedListNode *temp = list->head;
    while(temp->next != NULL)
    {
        funcPtr(temp->data);
        temp = temp->next; 
    }
    funcPtr(temp->data);
}

void freeLinkedList(LinkedList* list, listFunc funcPtr)
{
    LinkedListNode *temp = list->head;
    LinkedListNode *temp2;
    while(temp->next != NULL)
    {
        funcPtr(temp->data);
        temp2 = temp->next;
        free(temp);/*free node*/
        temp = temp2; 
    }
    free(list);/*free the LL*/
    list = NULL;
}

