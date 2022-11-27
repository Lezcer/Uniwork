#include "LinkedList.h"

LinkedList* createLinkedList()
/*Linked list constructor*/
{
    LinkedList* list =(LinkedList*)malloc(sizeof(LinkedList));
    /*Creating a pointer to list, mallocing space on the heap - typecasting this because 
    malloc returns a void pointer*/

    list->head = NULL;
    list->tail = NULL;
    list->count = 0;
    return list;
    /*set the values of the fields*/
}
int isEmpty(LinkedList* list)
{
    int empty;
    if(list->count == 0)
        empty = TRUE;
    else
        empty = FALSE;
    return empty;
}
void insertStart(LinkedList* list, void* entry)
{
    ListNode* node = (ListNode*)malloc(sizeof(ListNode));
    node->data = entry;
    /*Creating a pointer to node, mallocing space on the heap - typecasting this node*/

    if(isEmpty(list))
    {
        node->next = NULL;
        list->head = node;
        list->tail = node;
        /*set the node's next as the previous head
        set the new node as the head
        and because it is empty set the tail as the node*/
    }
    else
    {
        node->next = list->head;
        list->head = node;
        /*set the new node's next node as the previous head
        and then set the new head as the new node*/
    }
    list->count++;/*Incremening count*/
}
void* removeStart(LinkedList* list)
{
    ListNode* temp;
    void* tempData;
    /*creating temporary data holders*/

    if(isEmpty(list))
        printf("Linked list is already empty.");/*error*/
    else
    {
        temp = list->head;
        list->head = list->head->next;
        /*store the current head - that is to be removed
        set the new head as the node NEXt to the old head*/
        if(list->count == 1)
            list->tail = NULL;
            /*if the list had 1 node then set the tail to null as it will be empty*/
        list->count--;/*decrement count*/
        tempData = temp->data;
        free(temp);
        temp = NULL;
    }
    return tempData;
}
void insertLast(LinkedList* list, void* entry)
{
    ListNode* node = (ListNode*)malloc(sizeof(ListNode));
    node->data = entry;
    /*Creating a pointer to node, mallocing space on the heap - typecasting this node*/

    node->next = NULL;
    if(isEmpty(list))
    {
        list->head = node;
        list->tail = node;
        /*set the node's next as the previous head
        set the new node as the head
        and because it is empty set the tail as the node*/
    }
    else
    {
        list->tail->next = node;
        list->tail = node;
        /*set the old tail's next as the new node
        and then set the new tail of the list as new node*/
    }
    list->count++;/*Incremening count*/
    
}
void* removeLast(LinkedList* list)
{
    ListNode *temp, *prevTemp;
    void* tempData;
    /*creating temporary data holders*/

    if(isEmpty(list))
        printf("Linked list is already empty.");/*error*/
    else
    {
        temp = list->head;/*set temp to the head - for the iterator*/
        if(list->count ==1)
        {
            list->head = NULL;
            list->tail = NULL;
        }
        else
        {
            do
            {
                prevTemp = temp;
                temp = temp->next;
                /*preset previous to temp, then change temp to the node next to it*/
            }/*I want to see the node before the tail*/
            while(temp->next != NULL);

            list->tail = prevTemp;
            prevTemp->next = NULL;
            /*List's new tail is the old tail's previos node
            set the new tail's next node as NULL*/
        }
        list->count--;/*decrement count*/
        tempData = temp->data;
        free(temp);
        temp = NULL;
    }
    return tempData;
}
void* peekFirst(LinkedList* list)
{
    return list->head->data;
}
void* peekLast(LinkedList* list)
{
    return list->tail->data;
}
void printLinkedList(LinkedList* list, listFunc funcPtr)
{
    ListNode *temp = list->head;
    while(temp->next != NULL)/*Iterate thru each node*/
    {
        (*funcPtr)(temp->data);
        /*pass the data to the function thru function pointer*/
        temp = temp->next;/*iterate to the next node*/
    }
}
void freeLinkedList(LinkedList* list, listFunc funcPtr)
{
    ListNode *temp, *temp2;
    temp = list->head;
    while(temp->next != NULL)/*as always, an iterator*/
    {
        (*funcPtr)(temp->data);
        temp2 = temp->next;
        /*pass the current node's data to the function thru funcPtr
        and then iterate to the next node*/
        free(temp);
        printf("1\n");
        /*free the current node*/
        temp = temp2;
        /*now set the current node as the preset node from above - for re-iteration*/
    }
    (*funcPtr)(temp->data);
    free(temp);
    temp = NULL;
    free(list);/*free the list once done with nodes*/
    list = NULL;
    /*set the node to NULL*/
}
