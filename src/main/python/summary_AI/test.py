import os,sys
from dotenv import load_dotenv
import requests
import json
load_dotenv()

client_id = os.getenv("CLIENT_ID")
client_secret = os.getenv("CLIENT_SECRET")
web_URL = os.getenv("WEB_URL")

class AI_model:
    def __init__(self,
                 summerizer_url : str = 'https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize',
                 client_id : str = client_id,
                 client_secret : str = client_secret,
                 web_URL : str = web_URL):
        
        self.summerizer_url = summerizer_url
        self.client_id = client_id
        self.client_secret = client_secret
        self.web_URL = web_URL

    def generate(self, 
                 input_news_text_path : str = r'input_news.txt',
                 language : str = "ko",
                 text_type : str = "news",
                 tone : int = 2,
                 total_summary_sentence_count : int = 5
                 ):
        
        with open(input_news_text_path, 'r', encoding='utf-8') as file:
                input_news_txt = file.read()

        headers = {
            'Accept': 'application/json;UTF-8',
            'Content-Type': 'application/json;UTF-8',
            'X-NCP-APIGW-API-KEY-ID': client_id,
            'X-NCP-APIGW-API-KEY': client_secret
        }
        data = {
        "document": {
            "content": input_news_txt
        },
        "option": {
            "language": language, # ko: 한국어, ja: 일본어
            "model": text_type, # general: 일반 문서 요약, news: 뉴스 요약
            "tone": tone, # 0: 원문의 어투를 유지, 1: 해요체로 변환, 2: 정중체로 변환, 3: 명사형 종결체로 변환
            "summaryCount": total_summary_sentence_count # 요약된 문서의 문장 수
        }
        }
        response = requests.post(self.summerizer_url, headers=headers, data=json.dumps(data).encode('UTF-8'))
        rescode = response.status_code
        if(rescode == 200):
            result = json.loads(response.text)['summary']
            return result
        else:
            print(f"Error : " + response.text)
            return f"Error : " + response.text

if __name__ == "__main__":
    
    model = AI_model(summerizer_url = 'https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize')
    result = model.generate(input_news_text_path = r'input_news.txt', tone = 1, total_summary_sentence_count = 3)

    with open('input_news.txt', 'r', encoding='utf-8') as file:
        input_news_txt = file.read()
    print("*"*30, "<요약 전 원본 기사>", "", input_news_txt, sep = '\n')
    print("*"*30, "<AI 요약 후>", "", result, "*"*30, sep = '\n')


