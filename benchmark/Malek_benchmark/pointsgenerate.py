import os
import random
import math

# Define the starting number of points and the increment for each subsequent file
start_num_points = 200
increment = 10
total_files = 280
max_radius = 600
path = './Samples'

for filename in os.listdir(path):
    if filename.endswith(".points"):
        os.remove(os.path.join(path, filename))
        
def generate_points(num_points,radius=500):
    points = []
    for _ in range(num_points):
        angle = math.radians(random.randint(0, 360))  # Convert degrees to radians
        r = math.sqrt(random.randint(0, radius**2))  # Square root for uniform distribution
        x = r * math.cos(angle)
        y = r * math.sin(angle)
        points.append((int(x + 300), int(y + 100)))
    return points

def write_points_to_file(filename, points):
    with open(filename, 'w') as f:
        for point in points:
            f.write(f"{point[0]} {point[1]}\n")

for file_number in range(1, total_files + 1):
    num_points = start_num_points + (file_number - 1) * increment
    current_radius = start_num_points + math.ceil(file_number*0.3)*increment
    if current_radius > max_radius :
         points = generate_points(num_points, max_radius)
    else :
        points = generate_points(num_points, current_radius)
    filename = str(num_points) + ".points"
    file_path = os.path.join(path, filename)
    write_points_to_file(file_path, points)

