#include "io.h"

Character* readHero(char* filename)
{
    Character* hero;
    
    FILE* in = fopen(filename, "r");
    if(ferror(in))
        perror("Does the input file exist\n");

    hero = (Character*)malloc(sizeof(Character));
    fscanf(in, "%d,%d,%d,%d,%s",&(hero->hp), &(hero->atk), &(hero->def), &(hero->med), hero->name);
    
    if(ferror(in))
        perror("Error reading from file\n");
    
    fclose(in);
    return hero;  
}

LinkedList* readEnemies(char* filename)
{
    Character* enemy;
    LinkedList* enemies;
    int lines, i;

    FILE* in = fopen(filename, "r");
    if(ferror(in))
        perror("Does the input file exist\n");

    lines = countLines(filename);

    enemy = (Character*)malloc(sizeof(Character));
    enemies = createLinkedList();

    for(i=0; i<lines; i++)
    {
        fscanf(in, "%d,%d,%d,%[^\n]",&(enemy->hp), &(enemy->atk), &(enemy->def), enemy->name);
        insertLast(enemies, enemy);
    } 

    if(ferror(in))
        perror("Error reading from file\n");
    
    fclose(in);
    return enemies;  
    
}
int countLines(char* filename)
{
    int ch=0, lines=0;
    FILE* in = fopen(filename, "r");
    if(ferror(in))
        perror("Does the input file exist\n");

    while(!feof(in))
    {
        ch=fgetc(in);
        if(ch == '\n')/*end of line reached*/
            lines++;
    }
    fclose(in);

    return lines;
}
void gameStatus(Character* hero, LinkedList* enemies)
{
    printf("******STATUS UPDATE SCREEN*******\n");
    printf("\nHero:\n");
    printf("%s       HP:%d  ATK:%d  DEF:%d  MED:%d\n", hero->name, hero->hp, hero->atk,
                                                         hero->def, hero->med); 
    
    printf("\nEnemies:\n");
    printLinkedList(enemies, printEnemy);

}
void heroStatus(Character* hero)
{
    printf("%s\nHP:%d\nATK:%d\nDEF:%d\nMED:%d\n", hero->name, hero->hp, hero->atk,
    hero->def, hero->med); 
}
void enemyStatus(Character* enemy)
{
    printf("%s\n\tHP:%d\n\tATK:%d\n\tDEF:%d\n", enemy->name, enemy->hp, enemy->atk,
    enemy->def);
    if(enemy->hp == 0)
    {
        printf("%s has been slain!\n", enemy->name);
    } 
}
void damageStatus(char* atcName, char* defName, int damage, int crit)
{
    if(!crit)
    {
        printf("%s attacks %s, dealing %d damage!\n", atcName, defName, damage);
    }
    else
    {
        printf("%s attacks %s, critical hit! dealing %d damage!\n", atcName, defName, damage);
    }
}
void healing(char* hero)
{
    printf("%s is badly injured, eating 1 MED. HP is now restored!\n", hero);
}
void welcome()
{
    printf("Welcome to Salah's RPG");
}
void win(int didHeroWin, char* hero)
{
    if(didHeroWin)
    {
        printf("%s defeats all enemies! The world is saved.\n", hero);
    }
    else
    {
        printf("%s loses the battle! The world is doomed.\n", hero);
    }
}
void printEnemy(void* data)
{
    Character* enemy = (Character*) data;
    printf("%s         HP:%d  ATK:%d  DEF:%d\n", enemy->name, enemy->hp, enemy->atk, enemy->def); 
}
