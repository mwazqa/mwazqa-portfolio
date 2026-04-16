# Use the official Locust image as the base
FROM locustio/locust:2.24.0

# Set the working directory inside the container
WORKDIR /mnt/locust

# Copy the requirements file to install dependencies
COPY requirements.txt .

# Install Python dependencies without caching to keep the image slim
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the test scripts and files
COPY . .

# Expose the default Locust web interface port
EXPOSE 8089