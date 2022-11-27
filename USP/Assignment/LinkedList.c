/*Name: Salah Mahamod
  Purpose: Linked list code
  Notes for marker: Works and tested, but not using system calls.
                    Except for printf() replaced by write()
  Date: 23/05/2021
*/
#include "LinkedList.h"
LinkedList* createLinkedList()
{
    LinkedList* list =(LinkedList*)malloc(sizeof(LinkedList));

    list->head = NULL;
    list->tail = NULL;
    list->count = 0;
    return list;
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

    if(isEmpty(list))
    {
        node->next = NULL;
        list->head = node;
        list->tail = node;

    }
    else
    {
        node->next = list->head;
        list->head = node;
    }
    list->count++;
}
void* removeStart(LinkedList* list)
{
    ListNode* temp;
    void* tempData;
    if(isEmpty(list))
        write(1, "Linked list is already empty.\n", 32);
    else
    {
        temp = list->head;
        list->head = list->head->next;
        if(list->count == 1)
            list->tail = NULL;
        list->count--;
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
    node->next = NULL;
    if(isEmpty(list))
    {
        list->head = node;
        list->tail = node;
    }
    else
    {
        list->tail->next = node;
        list->tail = node;
    }
    list->count++;
    
}
void* removeLast(LinkedList* list)
{
    ListNode *temp, *prevTemp;
    void* tempData;

    if(isEmpty(list))
        write(1, "Linked list is already empty.\n", 32);
    else
    {
        temp = list->head;
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
            }
            while(temp->next != NULL);

            list->tail = prevTemp;
            prevTemp->next = NULL;

        }
        list->count--;
        tempData = temp->data;
        free(temp);
        temp = NULL;
    }
    return tempData;
}
void printLinkedList(LinkedList* list, listFunc funcPtr)
{
    ListNode *temp = list->head;
    while(temp->next != NULL)
    {
        (*funcPtr)(temp->data);
        temp = temp->next;
    }
    (*funcPtr)(temp->data);
}
void freeLinkedList(LinkedList* list, listFunc funcPtr)
{
    ListNode *temp, *temp2;
    temp = list->head;
    if(!isEmpty(list))
    {
        while(temp->next != NULL)
        {
            (*funcPtr)(temp->data);
            temp2 = temp->next;
            free(temp);
            temp = temp2;
        }
        (*funcPtr)(temp->data);
        free(temp);
        temp = NULL;
    }
    free(list);
    list = NULL;
    
}
