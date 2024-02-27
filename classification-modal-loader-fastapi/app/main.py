from transformers import BertModel, BertTokenizer
from fastapi import FastAPI
from pydantic import BaseModel
import torch

app = FastAPI()

class BERTClass(torch.nn.Module):
    def __init__(self):
        super(BERTClass, self).__init__()
        self.bert_model = BertModel.from_pretrained('bert-base-uncased', return_dict=True)
        self.dropout = torch.nn.Dropout(0.3)
        self.linear = torch.nn.Linear(768, 16)

    def forward(self, input_ids, attention_mask, token_type_ids):
        output = self.bert_model(input_ids, attention_mask=attention_mask, token_type_ids=token_type_ids)
        output_dropout = self.dropout(output.pooler_output)
        output = self.linear(output_dropout)
        return output
class TextIn(BaseModel):
    text: str

model_path = "model/sdg_classification_model_state.bin"
model_state_dict = torch.load(model_path, map_location=torch.device('cpu'))
model = BERTClass()
model.load_state_dict(model_state_dict)
model.eval()

tokenizer = BertTokenizer.from_pretrained('bert-base-uncased')

MAX_LEN = 512
target_list = ['SDG1', 'SDG2', 'SDG3'
               ,'SDG4', 'SDG5', 'SDG6',
               'SDG7', 'SDG8', 'SDG9',
               'SDG10', 'SDG11', 'SDG12',
               'SDG13', 'SDG14', 'SDG15',
               'SDG16',]


@app.post("/classify/")
async def classify_text(text_in: TextIn):
    encoded_text = tokenizer.encode_plus(
        text_in.text,
        max_length=MAX_LEN,
        add_special_tokens=True,
        return_token_type_ids=True,
        pad_to_max_length=True,
        return_attention_mask=True,
        return_tensors='pt',
    )

    device = torch.device('cpu')
    input_ids = encoded_text['input_ids'].to(device)
    attention_mask = encoded_text['attention_mask'].to(device)
    token_type_ids = encoded_text['token_type_ids'].to(device)
    output = model(input_ids, attention_mask, token_type_ids)
    output = torch.sigmoid(output).detach().cpu()
    output = output.flatten().round().numpy()

    labels = []
    for idx, p in enumerate(output):
        if p == 1:
            labels.append(target_list[idx])
    return {"text": text_in.text, "labels": labels}
