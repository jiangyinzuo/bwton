FROM python:3.8

WORKDIR /app

ADD bwtonqr ./requirements.txt /app/

RUN pip install -r requirements.txt -i https://pypi.doubanio.com/simple

CMD ["python", "app.py"]