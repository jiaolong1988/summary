# include <stdio.h>

//n������ ��A ����B  �ƶ���C
void hannuota(int n, char A, char B, char C){
/*
���n=4��ݹ��㷨���£�
	�����1������
		ֱ�ӽ�A�����ϵ����Ӵ�A�Ƶ�C
	����
		�Ƚ�A�����ϵ�[n-1 = 3]�����ӽ���C�Ƶ�B
		ֱ�ӽ�A�����ϵ����Ӵ�A�Ƶ�C
		���B�����ϵ�[n-1 = 3]�����ӽ���A�Ƶ�C		
*/
	if (1 == n){
		printf("�����Ϊ%d������ֱ�Ӵ�%c�����Ƶ�%c����\n", n, A, C);
	}
	else{
		hannuota(n-1, A, C, B);
		printf("�����Ϊ%d������ֱ�Ӵ�%c�����Ƶ�%c����\n", n, A, C);
		hannuota(n-1, B, A, C);
	}
}

int main(void){
	hannuota(4, 'A', 'B', 'C');
	return 0;
}