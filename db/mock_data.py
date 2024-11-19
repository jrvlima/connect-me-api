import psycopg2
from faker import Faker
import random
import os
from dotenv import load_dotenv

load_dotenv()
# Database connections
databases = {
    "users_db": {
        "dbname": "users_db",
        "user": os.getenv("POSTGRES_USER"),
        "password": os.getenv("POSTGRES_PASSWORD"),
        "host": "localhost",
        "port": 5432
    },
    "skills_db": {
        "dbname": "skills_db",
        "user": os.getenv("POSTGRES_USER"),
        "password": os.getenv("POSTGRES_PASSWORD"),
        "host": "localhost",
        "port": 5432
    },
}

faker = Faker()

BIGINT_MIN = 1
BIGINT_MAX = 2**63 - 1

def generate_long_id():
    return random.randint(BIGINT_MIN, BIGINT_MAX)


def populate_users_db():
    conn = psycopg2.connect(**databases["users_db"])
    cur = conn.cursor()

    for _ in range(10000):  # Insert 10,000 users
        person_id = generate_long_id()
        first_name = faker.first_name()
        last_name = faker.last_name()
        email = faker.email()

        created_at = faker.past_datetime()
        updated_at = faker.date_between(created_at, "now")

        user_id = generate_long_id()
        username = faker.user_name()
        password = faker.password()

        address_id = generate_long_id()
        full_address = faker.address().split("\n")
        if len(full_address) < 3:
            full_address.append("CA 94105")
        address = full_address[0]
        city = full_address[1]
        state = full_address[2].split(" ")[0]
        zip_code = full_address[2].split(" ")[1]

        cur.execute("INSERT INTO users (id, username, password, createdat, updatedat) VALUES (%s, %s, %s, %s, %s)", (user_id, username, password, created_at, updated_at))
        cur.execute("INSERT INTO persons (id, user_id, firstname, lastname, email, createdat, updatedat) VALUES (%s, %s, %s, %s, %s, %s, %s)", (person_id, user_id, first_name, last_name, email, created_at, updated_at))
        cur.execute("INSERT INTO addresses (id, person_id, address, city, state, country, zipcode, createdat, updatedat) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)", (address_id, person_id, address, city, state, "USA", zip_code, created_at, updated_at))

    conn.commit()
    cur.close()
    conn.close()

# Insert mock data into skills_db
def populate_skills_db():
    conn = psycopg2.connect(**databases["skills_db"])
    cur = conn.cursor()

    skill_categories = ["Plumbing", "Electrical", "Cleaning", "Gardening", "Babysitting"]
    for _ in range(10000):  # Insert 10,000 skills
        skill = faker.job()
        category = random.choice(skill_categories)
        cur.execute("SELECT id FROM skill_categories WHERE name = %s", (category,))
        category_id = cur.fetchone()[0]
        skill_id = generate_long_id()
        created_at = faker.past_datetime()
        updated_at = faker.date_between(created_at, "now")
        cur.execute("INSERT INTO skills (id, name, category_id, createdat, updatedat) VALUES (%s, %s, %s, %s, %s)", (skill_id, skill, category_id, created_at, updated_at))

    conn.commit()
    cur.close()
    conn.close()


def populate_category_skills_db():
    conn = psycopg2.connect(**databases["skills_db"])
    cur = conn.cursor()

    skill_categories = ["Plumbing", "Electrical", "Cleaning", "Gardening", "Babysitting"]
    for category in skill_categories:
        created_at = faker.past_datetime()
        updated_at = faker.date_between(created_at, "now")
        category_id = generate_long_id()
        cur.execute("INSERT INTO skill_categories (id, name, createdat, updatedat) VALUES (%s, %s, %s, %s)", (category_id, category, created_at, updated_at))

    conn.commit()
    cur.close()
    conn.close()


populate_category_skills_db()
# Populate databases
populate_skills_db()
populate_users_db()
print("Databases populated!")
