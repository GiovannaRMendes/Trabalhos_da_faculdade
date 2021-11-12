/**************************************************
*
* Nome do(a) estudante: Giovanna Rodrigues Mendes
* Trabalho 1
* Professor(a): Diego Padilha Rubert
*
*/

// Importando a biblioteca
#include <stdio.h>

/* Armazena os valores (o numero da capivara e a sua respectiva quantidade de ultrapassagens) */
typedef struct {
    int numero_capivaras;       // Numero da capivara
    int ultrapassagem;      // Numero de ultrapassagens
} capivara;     // Nome do tipo da variavel

/* A funcao troca_valores foi criada para modificar tanto os valores do numero da capivara como tambem a
quantidade das ultrapassagens. Assim, para o primeiro print queremos saber qual capivara chegou primeiro.
Portanto, a cada numero dado, apos o estabelecimento do total dos animais, a funcao e chamada para fazer
com que o numero da capivara designada "va para frente e ultrapasse a outra." */

// Pegando como parametro valores especificos (o numero da capivara) dentro de um vetor
void troca_valores (int *valor_1, int *valor_2) {
    int auxiliar;   // Valor que vai armazenar um numero para ser atribuido a outra variavel

    // Troca de valores
    auxiliar = *valor_1;
    *valor_1 = *valor_2;
    *valor_2 = auxiliar;
}

/* A funcao intercalacao foi estabelecida para intercalar os valores obtidos (tanto o numero das capivaras
como tambem o total de suas ultrapassagens) e assim ordenar o vetor consoante nos queremos (do numero
da capivara com maior valor de ultrapassagens ate o da com menor, sendo que caso elas tenham o mesmo
numero destas, a ordem sera crescente, ou seja, se as capivaras 1, 2 e 3 tenham 0 ultrapassagens, elas
serao printadas na ordem 1, 2 e 3). */

/* Pegando como parametro os valores: a primeira posicao (zero), o valor do meio (resultante da soma do
total de capivaras com o zero divido por 2), o total de capivaras e o vetor completo (capivaras),
sendo do tipo capivara, possuindo tanto o campo numero_capivaras e o ultrapassagem */
void intercalacao(int primeiro_valor, int valor_meio, int ultimo_total, capivara *vetor) {

    /* Atribuicao do primeiro valor (zero) para a variavel i, do valor do meio para a j e zero para a
    n (esta variavel sera responsavel por indicar o indice do vetor auxiliar) */
    int i = primeiro_valor, j = valor_meio, n = 0;

    /* O vetor_aux e um vetor auxiliar que vai ajudar no momento de mudanca da ordem das posicoes
    de determinados valores do vetor original que obtemos no inicio (na main) */
    capivara vetor_aux[ultimo_total];

    /* Estabelecemos um while para atribuir ao vetor auxiliar os valores ou da direita ou da esquerda
    dependendo da condicao que sera indicada. O laco vai ver os valores do vetor nos campos em todas as
    posicoes */
    while (i < valor_meio && j < ultimo_total) {

        /* Vamos estabelecer esta condicao para colocar o valores das maiores ultrapassagens na frente ou
        o menor numero da capivara na frente se ela tiver o mesmo valor de ultrapassagens que outra. Assim,
        a ordem sera crescente em relacao ao numero da capivara se obterem o mesmo numero de ultrapassagens.
        A segunda condicao e determinada por causa da modificacao no vetor original, podendo ter uma posicao
        de uma capivara com maior valor na frente de uma com menor, ou seja, a capivara 8 estar na frente da 7 */
        if (vetor[i].ultrapassagem > vetor[j].ultrapassagem || (vetor[i].numero_capivaras < vetor[j].numero_capivaras && vetor[i].ultrapassagem == vetor[j].ultrapassagem)) {
            vetor_aux[n] = vetor[i];
            i++;
        } else {
            vetor_aux[n] = vetor[j];
            j++;
        }
        n++;        // Vai aumentando o valor do indice do vetor auxiliar
    }

    /* Se ainda tiver numeros que nao chegaram ate a metade do vetor, esse laco sera executado */
    while (i < valor_meio) {
        vetor_aux[n] = vetor[i];
        i++;
        n++;
    }
    /* Se ainda tiver numeros que nao chegaram ate o final do vetor, esse laco sera executado */
    while (j < ultimo_total) {
        vetor_aux[n] = vetor[j];
        j++;
        n++;
    }

    /* Esse lco funciona para colocar no vetor original as mudancas obtidas (a ordenacao) no vetor auxiliar */
    for (i = primeiro_valor; i < ultimo_total; i++) {
        vetor[i] = vetor_aux[i-primeiro_valor];
    }
}

/* A funcao recursiva merge_sort serve para ordenar o vetor nos dois campos estabelecidos (numero da capivara
e de ultrapassagens). Ela ira estabelecer um meio ate o valor atribuido ao valor1 ser menor que o total (numero
de capivaras) - 1, ou seja, quando o indice for o penultimo do vetor (exemplo: se forem 5 capivaras, o ultimo
indice sera 4 e o penultimo - o que a condicao utiliza - e o 3). */

/* Os parametros sao o primeiro valor de um vetor (no caso de um que possuir 5 valores, o primeiro sera o zero
para a chamada, por exemplo, do primeiro merge sort, no caso da segunda chamada, 2), o ultimo (utilizando o mesmo
exemplo, na primeira chamada sera o 2 e na segunda, o 5), alem do vetor completo com todos os campos */
void merge_sort(int valor1, int total, capivara *vetor) {
    int meio;       // Declaracao da variavel meio

    /* Condcao a ser atendida para que a funcao recursiva merge_sort possa obter (nao retornar, pois e void e
    nao int), o vetor ordenado, sendo ele do tipo capivara e possuindo os campos numero_capivaras e ultrapassagem */
    if (valor1 < total-1) {
        meio = (total+valor1)/2;
        merge_sort(valor1, meio, vetor);
        merge_sort(meio, total, vetor);
        intercalacao(valor1, meio, total, vetor);
    }
}

/* Funcao principal da qual vamos chamar as funcoes, obter informacoes e imprimir, na tela, os valores desejados */
int main(void) {

    /* Declaracao das variaveis para estabelecer o total de capivaras (quantidade_capivaras) e o numero de
    ultrapassagens (ultrapassagens) */
    int quantidade_capivaras, ultrapassagens;

    scanf("%d", &quantidade_capivaras);         // Le quantas capivaras estarao na maratona

    /* A partir da atribuicao do total desse animal, vamos declarar um vetor para as capivaras utilizando registro.
    O tipo estabelecido anteriormente e capivara e sera usado para colocar os valores do vetor capivaras conforme o
    total delas (quantidade_capivaras) nos campos numero_capivaras e ultrapassagem */
    capivara capivaras[quantidade_capivaras];

    /* A partir da quantidade de capivaras, vamos estabelecer os numeros das posicoes de cada capivara na linha de
    largada e seu respectivo numero de ultrapassagens, que no caso nao comecou a corrida, por isso todas tem zero atribuido */
    for (int n = 0; n < quantidade_capivaras; n++) {
        capivaras[n].numero_capivaras = n+1;
        capivaras[n].ultrapassagem = 0;
    }

    /* Vamos ler qual capivara ultrapassou ate o fim do arquivo */
    while (scanf("%d", &ultrapassagens) != EOF) {

        /* Fazendo uma busca para ver o indice do numero da capivara que ultrapassou (ultrapassagens) e declarando i*/
        int i;
        for (i = 0; i < quantidade_capivaras && capivaras[i].numero_capivaras != ultrapassagens; i++);

        ultrapassagens = i;

        /* Aumenta a ultrapassagem de uma capivara */
        capivaras[i].ultrapassagem++;

        /* Troca os valores da capivara que foi responsavel pela ultrapassagem e a anterior (a ultrapassada),
        como se ela fosse para a frente no vetor */
        troca_valores(&capivaras[ultrapassagens].ultrapassagem, &capivaras[ultrapassagens-1].ultrapassagem);
        troca_valores(&capivaras[ultrapassagens].numero_capivaras, &capivaras[ultrapassagens-1].numero_capivaras);
    }
    /* Printando a ordem do final da corrida */
    for (int p1 = 0; p1 < quantidade_capivaras; p1++) {

        /* Condicao feita para quando for imprimir o ultimo numero nao tenha um espaco depois */
        if (p1 == 0) {
            printf("%d", capivaras[p1].numero_capivaras);
        } else {
            printf(" %d", capivaras[p1].numero_capivaras);
        }
    }
    printf("\n");       // Pula a linha

    /* Vai chamar a funcao responsavel pela ordenacao do vetor capivaras e seus campos */
    merge_sort(0, quantidade_capivaras, capivaras);

    /* Printando a ordem das capivaras conforme o valor das ultrapassagens */
    for (int p2 = 0; p2 < quantidade_capivaras; p2++) {

        /* Condicao feita para quando for imprimir o ultimo numero nao tenha um espaco depois */
        if (p2 == 0) {
            printf("%d", capivaras[p2].numero_capivaras);
        } else {
            printf(" %d", capivaras[p2].numero_capivaras);
        }
    }
    printf("\n");       // Pula a linha

    return 0;       // Encerra o codigo
}
