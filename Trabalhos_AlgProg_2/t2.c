/**************************************************
*
* Nome dos(as) estudantes: Giovanna Rodrigues Mendes e Roney Felipe de Oliveira Miranda
* Trabalho 2
* Professor(a): Diego Padilha Rubert
*
*/

// Importando as bibliotecas necessarias.
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#define MAX_OP 3 //Definindo a constante MAX_OP com o valor 3.
#define MAX_LINE 128 //Definindo a constante MAX_LINE com o valor 128.

// Registro para as calulas
typedef struct instr {

    char op[MAX_OP+1];    /* Operacao */
    char reg1[MAX_OP+1];  /* Registrador do operando 1, se registrador */

    int val1;             /* Valor do operando 1, se inteiro */
    char reg2[MAX_OP+1];  /* Registrador do operando 2, se houver */

    struct instr *prev;   /* Anterior */
    struct instr *next;   /* Proximo */

} instruction;

//Funcao responsavel por interpretar o comando mov em capivarition.
void mov_interpretar (instruction *lista, int *acc, int *dat, int *ext, int *pc) {
    /*Se a primeira posicao do reg1 for diferente de '\0' significa que recebemos um char como
    primeiro operando, ou seja, um registrador.*/
   if (lista->reg1[0] != '\0') {

       //Se reg1 recebeu acc e reg2 recebeu dat, entao dat = acc.
        if ((strcmp(lista->reg1, "acc") == 0) && ((strcmp(lista->reg2, "dat") == 0))) {
            *dat = *acc;
        }

        //Se reg1 recebeu acc e reg2 recebeu ext, entao ext = acc.
        else if ((strcmp(lista->reg1, "acc") == 0) && ((strcmp(lista->reg2, "ext") == 0))) {
            *ext = *acc;
        }

        //Se reg1 recebeu acc e reg2 recebeu pc, entao pc = acc.
        else if ((strcmp(lista->reg1, "acc") == 0) && ((strcmp(lista->reg2, "pc") == 0))) {
            *pc = *acc;
        }

        //Se reg1 recebeu dat e reg2 recebeu acc, entao acc = dat.
        else if ((strcmp(lista->reg1, "dat") == 0) && ((strcmp(lista->reg2, "acc") == 0))) {
            *acc = *dat;
        }

        //Se reg1 recebeu dat e reg2 recebeu ext, entao ext = dat.
        else if ((strcmp(lista->reg1, "dat") == 0) && ((strcmp(lista->reg2, "ext") == 0))) {
            *ext = *dat;
        }

        //Se reg1 recebeu dat e reg2 recebeu pc, entao pc = dat.
        else if ((strcmp(lista->reg1, "dat") == 0) && ((strcmp(lista->reg2, "pc") == 0))) {
            *pc = *dat;
        }

        //Se reg1 recebeu ext e reg2 recebeu acc, entao acc = ext.
        else if ((strcmp(lista->reg1, "ext") == 0) && ((strcmp(lista->reg2, "acc") == 0))) {
            *acc = *ext;
        }

        //Se reg1 recebeu ext e reg2 recebeu dat, entao dat = ext.
        else if ((strcmp(lista->reg1, "ext") == 0) && ((strcmp(lista->reg2, "dat") == 0))) {
            *dat = *ext;
        }

        //Se reg1 recebeu ext e reg2 recebeu pc, entao pc = ext.
        else if ((strcmp(lista->reg1, "ext") == 0) && ((strcmp(lista->reg2, "pc") == 0))) {
            *pc = *ext;
        }

        //Se reg1 recebeu pc e reg2 recebeu acc, entao acc = pc.
        else if ((strcmp(lista->reg1, "pc") == 0) && ((strcmp(lista->reg2, "acc") == 0))) {
            *acc = *pc;
        }

        //Se reg1 recebeu pc e reg2 recebeu dat, entao dat = pc.
        else if ((strcmp(lista->reg1, "pc") == 0) && ((strcmp(lista->reg2, "dat") == 0))) {
            *dat = *pc;
        }

        //Se reg1 recebeu pc e reg2 recebeu pc, entao ext = pc.
        else if ((strcmp(lista->reg1, "pc") == 0) && ((strcmp(lista->reg2, "ext") == 0))) {
            *ext = *pc;
        }

    }
    /*Se a primeira posicao do reg1 for igual a '\0' significa que recebemos um inteiro como
    primeiro operando.*/
    else {
        //Se reg2 for igual a acc, entao acc = lista->val1.
        if((strcmp(lista->reg2, "acc") == 0)) {
            *acc = lista->val1;
        }

        //Se reg2 for igual a dat, entao dat = lista->val1.
        else if ((strcmp(lista->reg2, "dat") == 0)) {
            *dat = lista->val1;
        }

        //Se reg2 for igual a ext, entao ext = lista->val1.
        else if ((strcmp(lista->reg2, "ext") == 0)) {
            *ext = lista->val1;

        }

        //Se reg2 for igual a pc, entao pc = lista->val1.
        else if ((strcmp(lista->reg2, "pc") == 0)) {
            *pc = lista->val1;

        }
    }
}

//Funcao responsavel por interpretar o comando add em capivariton.
void add_interpretar(instruction *lista, int *acc, int *dat, int *ext, int *pc) {

    /*Se a primeira posicao do reg1 for diferente de '\0' significa que recebemos um char como
    primeiro operando, ou seja, um registrador.*/
    if (lista->reg1[0] != '\0') {

        //Se reg1 for igual a dat, entao acc = acc + dat.
        if((strcmp(lista->reg1, "dat") == 0)) {
            *acc += *dat;
        }

        //Se reg1 for igual a ext, entao acc = acc + ext.
        else if ((strcmp(lista->reg1, "ext") == 0)) {
            *acc += *ext;
        }

        //Se reg1 for igual a acc, entao acc = acc + acc.
        else if ((strcmp(lista->reg1, "acc") == 0)) {
            *acc += *acc;
        }

        //Se reg1 for igual a pc, entao acc = acc + pc.
        else if ((strcmp(lista->reg1, "pc") == 0)) {
            *acc += *pc;
        }
    }

    /*Se a primeira posicao do reg1 for igual a '\0' significa que recebemos um inteiro como
    primeiro operando.*/
    else {
        //Entao acc e somado ao inteiro dentro de val1.
        *acc += lista->val1;
    }
}

//Funcao responsavel por interpretar o comando sub em capivariton.
void sub_interpretar(instruction *lista, int *acc, int *dat, int *ext, int *pc) {

    /*Se a primeira posicao do reg1 for diferente de '\0' significa que recebemos um char como
    primeiro operando, ou seja, um registrador.*/
    if (lista->reg1[0] != '\0') {

        //Se reg1 for igual a dat, entao acc = acc - dat.
        if((strcmp(lista->reg1, "dat") == 0)) {
           *acc -= *dat;
        }

        //Se reg1 for igual a ext, entao acc = acc - ext.
        else if ((strcmp(lista->reg1, "ext") == 0)) {
            *acc -= *ext;
        }

        //Se reg1 for igual a acc, entao acc = acc + acc.
        else if ((strcmp(lista->reg1, "acc") == 0)) {
            *acc += *acc;
        }

        //Se reg1 for igual a pc, entao acc = acc + pc.
        else if ((strcmp(lista->reg1, "pc") == 0)) {
            *acc += *pc;
        }
    }

    /*Se a primeira posicao do reg1 for igual a '\0' significa que recebemos um inteiro como
    primeiro operando.*/
    else {
        //Entao acc e subtraido do inteiro dentro de val1.
        *acc -= lista->val1;
    }

}

//Funcao responsavel por interpretar o comando mul em capivariton.
void mul_interpretar(instruction *lista, int *acc, int *dat, int *ext, int *pc) {

    /*Se a primeira posicao do reg1 for diferente de '\0' significa que recebemos um char como
    primeiro operando, ou seja, um registrador.*/
    if (lista->reg1[0] != '\0') {

        //Se reg1 for igual a dat, entao acc = acc * dat.
        if((strcmp(lista->reg1, "dat") == 0)) {
            *acc *= *dat;
        }

        //Se reg1 for igual a ext, entao acc = acc * ext.
        else if ((strcmp(lista->reg1, "ext") == 0)) {
            *acc *= *ext;
        }

        //Se reg1 for igual a acc, entao acc = acc + acc.
        else if ((strcmp(lista->reg1, "acc") == 0)) {
            *acc += *acc;
        }

        //Se reg1 for igual a pc, entao acc = acc + pc.
        else if ((strcmp(lista->reg1, "pc") == 0)) {
            *acc += *pc;
        }
    }

    /*Se a primeira posicao do reg1 for igual a '\0' significa que recebemos um inteiro como
    primeiro operando.*/
    else {
        //Entao acc e multiplicado pelo inteiro dentro de val1.
        *acc *= lista->val1;
    }

}

//Funcao responsavel por interpretar o comando div em capivariton.
void div_interpretar(instruction *lista, int *acc, int *dat, int *ext, int *pc) {

    /*Se a primeira posicao do reg1 for diferente de '\0' significa que recebemos um char como
    primeiro operando, ou seja, um registrador.*/
    if (lista->reg1[0] != '\0') {

        //Se reg1 for igual a dat, entao acc = acc / dat.
        if((strcmp(lista->reg1, "dat") == 0)) {
            *acc /= *dat;
        }

        //Se reg1 for igual a ext, entao acc = acc / ext.
        else if ((strcmp(lista->reg1, "ext") == 0)) {
            *acc /= *ext;
        }

        //Se reg1 for igual a acc, entao acc = acc + acc.
        else if ((strcmp(lista->reg1, "acc") == 0)) {
            *acc += *acc;
        }

        //Se reg1 for igual a pc, entao acc = acc + pc.
        else if ((strcmp(lista->reg1, "pc") == 0)) {
            *acc += *pc;
        }
    }

    /*Se a primeira posicao do reg1 for igual a '\0' significa que recebemos um inteiro como
    primeiro operando.*/
    else {
        //Entao acc e dividido pelo inteiro dentro de val1.
        *acc /= lista->val1;
    }

}

//Funcao responsavel por interpretar o comando div em capivariton.
void mod_interpretar(instruction *lista, int *acc, int *dat, int *ext, int *pc) {

    /*Se a primeira posicao do reg1 for diferente de '\0' significa que recebemos um char como
    primeiro operando, ou seja, um registrador.*/
    if (lista->reg1[0] != '\0') {

        //Se reg1 for igual a dat, entao acc = acc % dat.
        if((strcmp(lista->reg1, "dat") == 0)) {
            *acc %= *dat;
        }

        //Se reg1 for igual a ext, entao acc = acc % ext.
        else if ((strcmp(lista->reg1, "ext") == 0)) {
            *acc %= *ext;
        }

        //Se reg1 for igual a acc, entao acc = acc + acc.
        else if ((strcmp(lista->reg1, "acc") == 0)) {
            *acc += *acc;
        }

        //Se reg1 for igual a pc, entao acc = acc + pc.
        else if ((strcmp(lista->reg1, "pc") == 0)) {
            *acc += *pc;
        }
    }
    else {
        /*Entao acc e dividido pelo inteiro dentro de val1 e o resto da divisao
        e colocado em acc.*/
        *acc %= lista->val1;
    }

}

//Funcao responsavel por interpretar os comandos jmp, jeq, jlt e jgt em capivariton.
void all_j_interpretar(instruction **lista, int *acc, int *dat, int *ext, int *pc) {

    //A quantidade de linhas que devem ser puladas e colocada dentro de avanco.
    int avanco = (*lista)->val1;

    /*Se avanco foi positivo, maior que zero, entao o programa deve avancar "avanco" celulas (linhas
    em capivariton). Sempre aumentando o valor de pc pois ele controla, em tese, em qual linha de
    interpretacao estamos.*/
    if(avanco > 0) {
        for(int cont = 0; cont < avanco; cont++) {
            *lista = (*lista)->next;
            (*pc)++;
        }
    }

    /*Se avanco foi negativo, menor que zero, entao o programa deve regredir "avanco" celulas (linhas
    em capivariton). Sempre diminuindo o valor de pc pois ele controla, em tese, em qual linha de
    interpretacao estamos.*/
    else {
        for(int cont = 0; cont > avanco; cont--) {
            *lista = (*lista)->prev;
            (*pc)--;
        }
    }
    /*Como avancamos ou regredimos o tanto de vezes que val1 determinou, precisamos sempre
    voltar uma celula pois quando retornarmos a funcao interpretar_capivariton ela ira iterar uma celula,
    ou seja, pulariamos a celula alcancada nessa funcao. Diante disso, voltamos uma celula e diminuimos
    o pc.*/
    (*lista) = (*lista)->prev;
    (*pc)--;

}

//Funcao responsavel por interpretar o comandos prt em capivariton.
void prt_interpretar(instruction *lista, int *acc, int *dat, int *ext, int *pc) {

    //Se reg1 possui o valor de acc, entao acc deve ser printado.
    if((strcmp(lista->reg1, "acc") == 0)) {
        printf("%d\n", *acc);
    }

    //Se reg1 possui o valor de dat, entao dat deve ser printado.
    else if((strcmp(lista->reg1, "dat") == 0)) {
        printf("%d\n", *dat);
    }

    //Se reg1 possui o valor de ext, entao ext deve ser printado.
    else if((strcmp(lista->reg1, "ext") == 0)) {
        printf("%d\n", *ext);
    }

    //Se reg1 possui o valor de pc, entao pc deve ser printado.
    else if((strcmp(lista->reg1, "pc") == 0)) {
        printf("%d\n", *pc);
    }

    /*Se a primeira posicao do reg1 for igual a '\0' significa que o recebemos um inteiro como
    primeiro operando.*/
    else if (lista->reg1[0] == '\0') {

        //Entao o inteiro recebido via val1 deve ser exibido.
        printf("%d\n", lista->val1);
    }
}

//Funcao responsavel por interpretar o comandos recebidos em capivariton.
void interpretar_capivariton(instruction *lista) {

    //P sera o ponteiro que andara pela lista duplamente encadeada.
    instruction *p;

    //Declaracao dos registradores acc, dat, ext e pc. Todos inicializados em 0.
    int acc = 0, dat = 0, ext = 0, pc = 0;

    /* For incubido de percorrer toda a lista duplamente encadeada e
    adicionando, no final, pc toda vez que o laco e executado. */
    for (p = lista->next; p != NULL; p = p->next) {

        //Verifica se o campo op da celula dessa iteracao e igual ao comando mov.
        if (strcmp(p->op, "mov") == 0) {
            //Se sim, a funcao responsavel por interpretar o comando mov e chamada.
            mov_interpretar(p, &acc, &dat, &ext, &pc);
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando add.
        else if (strcmp(p->op, "add") == 0) {
            //Se sim, a funcao responsavel por interpretar o comando add e chamada.
            add_interpretar(p, &acc, &dat, &ext, &pc);
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando sub.
        else if (strcmp(p->op, "sub") == 0) {
            //Se sim, a funcao responsavel por interpretar o comando sub e chamada.
            sub_interpretar(p, &acc, &dat, &ext, &pc);
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando mul.
        else if (strcmp(p->op, "mul") == 0) {
            //Se sim, a funcao responsavel por interpretar o comando mul e chamada.
            mul_interpretar(p, &acc, &dat, &ext, &pc);
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando div.
        else if (strcmp(p->op, "div") == 0) {
            //Se sim, a funcao responsavel por interpretar o comando div e chamada.
            div_interpretar(p, &acc, &dat, &ext, &pc);
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando mod.
        else if (strcmp(p->op, "mod") == 0) {
            //Se sim, a funcao responsavel por interpretar o comando mod e chamada.
            mod_interpretar(p, &acc, &dat, &ext, &pc);
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando jmp.
        else if (strcmp(p->op, "jmp") == 0) {
            /*Se sim, funcao responsavel por interpretar os comandos jmp,
            jeq, jlt e lgt e chamada.*/
            all_j_interpretar(&p, &acc, &dat, &ext, &pc);
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando jeq.
        else if (strcmp(p->op, "jeq") == 0) {
            if (acc == 0) {
                /*Se sim, e caso acc seja igual e 0, entao a funcao responsavel
                por interpretar os comando jmp, jeq, jlt e lgt e chamada.*/
                all_j_interpretar(&p, &acc, &dat, &ext, &pc);
            }
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando jlt.
        else if (strcmp(p->op, "jlt") == 0) {
            if (acc < 0) {
                /*Se sim, e caso acc seja menor que 0, entao e a funcao responsavel
                por interpretar os comandos jmp, jeq, jlt e lgt e chamada.*/
                all_j_interpretar(&p, &acc, &dat, &ext, &pc);
            }
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando jgt.
        else if (strcmp(p->op, "jgt") == 0) {
            if (acc > 0) {
                /*Se sim, e caso acc seja maior que 0, entao a funcao responsavel
                por interpretar os comandos jmp, jeq, jlt e jgt e chamada.*/
                all_j_interpretar(&p, &acc, &dat, &ext, &pc);
            }
        }

        //Verifica se o campo op da celula dessa iteracao e igual ao comando prt.
        else if (strcmp(p->op, "prt") == 0) {
            //Se sim, a funcao responsavel por interpretar o comando prt e chamada.
            prt_interpretar(p, &acc, &dat, &ext, &pc);
        }
        pc++;
    }
}

/* A funcao insere_lista ira inserir mais uma celula a lista duplamente encadeada conforme for chamada. */
void insere_lista(instruction *nova, instruction *lista) {

    // Declarando ponteiros para nos auxiliar na insercao.
    instruction *p, *q;

    // Apontando os ponteiros aos seus locais respectivos, isto e, um depois do outro para facilitar na insercao da celula.
    p = lista;
    q = lista->next;

    // Condicao para ver onde para e, portanto saindo do laco quando atinge a condicao q == NULL.
    while (q != NULL) {
        p = q;
        q = q->next;
    }

    // Redefinindo onde a ultima celula aponta para incluir a nova criada que vira por ultimo.
    nova->prev = p;
    nova->next = q;
    p->next = nova;

    // Verificando se q e diferente de NULL.
    if (q != NULL) {
        q->prev = nova;
    }
}


/* A funcao ler_arquivo vai compreender o que esta dentro do arquivo.cap e colocar no seu devido local
na celula declarada no inicio do codigo. */
void ler_aquivo(instruction *lista, char *argv[]) {

    // Usando um contador para controlar os locais da celula serao usados.
    int cont = 0;

    // Declarando uma nova celula chamada nova com o tipo instruction e alocando um espaco na memoria.
    instruction *nova;
    nova = (instruction *) malloc(sizeof (instruction));

    /* Declarando um vetor chamado caracter que vai pegar os nomes das operacoes, instrucoes, inclusive dos
    numericos presentes no arquivo. Alem disso, declaramos o verificador que sera usado para comparar se
    possuimos um '\n' ou nao. */
    char caracter[MAX_LINE+1];

    // Ponteiro para andar pelo arquivo.
    FILE *f;

    //Comando para abrir o arquivo.
    f = fopen(argv[1],"r");

    /* Laco feito para lermos string por string ate o EOF e atribuindo ao verificador o que restar, por
    exemplo um '\n' ou um espaco. */
    while (fscanf(f, " %s", caracter) != EOF) {

        // Verificando se o que ha em caracter inicia com um #.
        if (caracter[0] == '#') {

            // Lendo toda a linha ate o final dela ('\n') e nao utilizando, pois e considerado comentario.
            fscanf(f, "%*[^\n] ");

        }

        // Verificando se o que ha em caracter nao inicia com um #.
        else {

            /* Caso seja os operadores, colocamos a nova->op o que foi atribuido a string caracter e colocando '\0' para evitar o lixo
            de memoria que pode ter, ja que em alguns casos nao a utilizados o reg1 ou o reg2 ou ambos. */
            if (cont == 0) {
                strcpy(nova->op, caracter);
                cont++;
                nova->reg1[0] = '\0';
                nova->reg2[0] = '\0';
            }
            /* Caso nao seja as instrucoes, serao os operandos ou o valor inteiro atribuido a algum registrador ou ambos, por
            isso fazemos as comparacoes utlizando o cont. Contudo, antes disso, comparamos se a string e um conjunto de caracteres
            ou e um numero com/sem sinal. Assim, vemos se estamos na segunda ou ultima atribuicao colocando no reg1 ou reg2,
            respectivamente, se nao houver inteiro. Se a string for um numero, sera convertida em inteiro pela funcao atoi e
            colocada em nova->val1. */
            else {

                if ((caracter[0] >= 97) && (caracter[0] <= 122)) {
                    if (cont == 1) {
                        strcpy(nova->reg1, caracter);

                    }
                    else {
                        strcpy(nova->reg2, caracter);
                    }

                }
                else {
                    nova->val1 = atoi(caracter);
                }
                cont++;
            }

        }

        /*Caso nova->op for igual a mov, ou seja, mov e o operador. Dessa forma, iremos receber dois
        operandos, por isso cont > 2.*/
        if (strcmp(nova->op, "mov") == 0) {
            if (cont > 2) {
                cont = 0;
                insere_lista(nova, lista);
                nova = (instruction *) malloc(sizeof (instruction));
            }

        }
        /*Caso nova->op for diferente de mov, iremos receber operadores que necessitam apenas de um
        operando, por isso cont > 1.*/
        else if (cont > 1) {
            cont = 0;
            insere_lista(nova, lista);
            nova = (instruction *) malloc(sizeof (instruction));
        }

    }
    // Funcao para fechar o arquivo.
    fclose(f);

}

/* Funcao que recebe dois parametros que serao utilizados para abrir o arquivo. */
int main (int argc, char *argv[]) {

    //Declarando a lista duplamente encadeada com cabeca.
    instruction *lista;
    lista = (instruction *) malloc(sizeof (instruction));
    lista->prev = NULL;
    lista->next = NULL;

    /* Chamando a funcao ler_arquivo para ler o arquivo passando tanto a lista como o argv. */
    ler_aquivo(lista, argv);

    /* Chamando a funcao ler_arquivo para ler o arquivo passando a lista. */
    interpretar_capivariton(lista);

    // Encerrando o programa.
    return 0;

}
