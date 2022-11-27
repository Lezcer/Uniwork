#ifndef IO_H
#define IO_H

#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"

typedef struct Character
{
    int hp;
    int atk;
    int def;
    int med;
    char name[50];
} Character;

Character* readHero(char* filename);
LinkedList* readEnemies(char* filename);
int countLines(char* filename);
void gameStatus(Character* hero, LinkedList* enemies);
void printEnemy(void* data);
void welcome();
void heroStatus(Character* hero);
void enemyStatus(Character* enemy);
void damageStatus(char* atcName, char* defName, int damage, int crit);
void healing(char* hero);
void win(int didHeroWin, char* hero);


#endif
