package com.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dao.StudentRepository;
import com.model.Batch;
import com.model.Student;

@Service
public class StudentServiceImplementation implements StudentService
{
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	JavaMailSender mailSender;
	
	public Student allotBatch(int id, Batch batch)
	{
		Student student = studentRepo.findById(id).orElse(null);
		Student alloted;
		if(student != null)
		{
			student.setBatch(batch);
			alloted = studentRepo.save(student);
			System.out.println("Batch Alloted");
			return alloted;
		}
		System.out.println("Batch Not Alloted");
		return null;
	}
	
	@Override
	public Map<Boolean,String> registerStudent(Student student)
	{
		Student findEmail = studentRepo.findByEmail(student.getEmail());
		Student findmobile = studentRepo.findByMobile(student.getMobile());
		Map<Boolean,String> map = new HashMap<>();
		String msg = "";
		String charSet = "1234567890"+"@#$&*";
		
		Random rand = new Random();
		if(findEmail == null && findmobile == null)
		{
			System.out.println(findEmail);
			System.out.println(findmobile);
			String randomString = "";
			String name = student.getName();
			for(int j=0; j<name.length(); j++)
			{
				if(name.charAt(j) == ' ')
					break;
				else
					randomString += name.charAt(j);
			}
			for(int i=0; i<4; i++)
			{
				randomString += charSet.charAt(rand.nextInt(charSet.length()));
			}
			student.setPassword(randomString);
			student.setDate(java.time.LocalDate.now());
			student.setStatus("active");
			studentRepo.save(student);
			msg = "Student Registered Successfully";
			map.put(true, msg);
			//sendWelcomeEmail(student.getEmail(), student.getName(),student.getEmail(),randomString);

		}
		else
		{
			msg = "Student Already Exsists with given email";
			map.put(false, msg);
		}
		return map;
	}
	
	
	private void sendWelcomeEmail(String email, String name, String email2, String randomString)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Welcome to Symbiosis Digital Acadmey");
		message.setText("Dear "+name+", We welcome your to the Symbiosis Digital Acadmey. You’ve embarked on a journey to gain valuable skills and kickstart your career.\n Following are your login credentials. Login to your account and fill out the admission form.\n Username :"+email+"\nPassword :"+randomString);
		mailSender.send(message);
	}


	@Override
	public Object addEnquiry(Student student)
	{
		String phone = student.getMobile();
		ArrayList<Student> obj = (ArrayList) studentRepo.findAll();
		Object ret = null;
		int flag = 0;
		for(Student mob : obj)
		{
			if(mob.getMobile().equals(phone))
			{
				flag = 1;
				break;
			}
		}
		if(flag == 0)
			ret = studentRepo.save(student);
		else
			ret = "Enquiry Already Exists";
		
		return ret;
			
	}
	
	
	
	public ArrayList<Student> getAllEnquiry()
	{
		return (ArrayList)studentRepo.findAll();
	}



	@Override
	public Student getById(int id)
	{
		Student student = studentRepo.findById(id).orElse(null);
		System.out.println("inside GetById"+student.getName());
		return student;
	}



	@Override
	public ArrayList<Student> getByName(String name)
	{
		ArrayList<Student> studentList = new ArrayList<>();
		ArrayList<Student> names = (ArrayList<Student>) studentRepo.findAll();
		for(Student student : names)
		{
			if((student.getName().toLowerCase()).contains(name))
			{
				studentList.add(student);
			}
		}
		
        return studentList;
    }



	@Override
	public Map<Boolean,String> deleteStudent(int id)
	{
		Student student = studentRepo.findById(id).orElse(null);
		Map<Boolean,String> map = new HashMap<>();
		if(student != null)
		{
			studentRepo.deleteById(id);
			map.put(true, "Student with ID="+id+" Deleted Successfully");
			return map;
		}
		else
		{
			map.put(false, "DELETION FAILED...Student did not Found");
		}
		return map;
	}



	@Override
	public Student updateStudent(Student student)
	{
		Student s = studentRepo.findById(student.getId()).orElse(null);
		if(student != null)
		{
			System.out.println("Inside Updation");
			if(student.getAadhar() != null)
				s.setAadhar(student.getAadhar());
			if(student.getCourse() != null)
				s.setCourse(student.getCourse());
			if(student.getDob() != null)
				s.setDob(student.getDob());
			if(student.getEmail() != null)
				s.setEmail(student.getEmail());
			if(student.getFatherName() != null)
				s.setFatherName(student.getFatherName());
			if(student.getMotherName() != null)
				s.setMotherName(student.getMotherName());
			if(student.getMobile() != null)
				s.setMobile(student.getMobile());
			if(student.getGender() != null)
				s.setGender(student.getGender());
			if(student.getPermanentAddr() != null)
				s.setPermanentAddr(student.getPermanentAddr());
			if(student.getPresentAddr() != null)
				s.setPresentAddr(student.getPresentAddr());
			if(student.getParentMobile() != null)
				s.setParentMobile(student.getParentMobile());

			s.setEducationList(student.getEducationList());
			s.setStatus(student.getStatus());
			studentRepo.save(s);
		}
		System.out.println("Outside Updation");
		return s;
	}


	@Override
	public java.util.List<Student> sortByStatus(String status)
	{
		return studentRepo.findAllByStatusOrderByNameAsc(status);
	}


	@Override
	public Student login(String username, String password)
	{
		Student student = studentRepo.findByEmail(username);
		if(student != null)
		{
			System.out.println("Student Exists");
			System.out.println(student.getName());
			System.out.println(student.getPassword());
			if(student.getPassword().equals(password))
			{
				System.out.println("Correct Password");
				return student;
			}
			else
			{
				System.out.println("Incorrect Password");
			}
		}
		else
			System.out.println("Student Does Not Exists");
		
		return null;
	}


	@Override
	public ArrayList<Student> findStudentToAddInBatch(String course)
	{
		ArrayList<Student> students = studentRepo.findStudentsByCourseAndBatchIdIsNull(course);
		return students;
	}

	@Override
	public long CountStudent()
	{
		long count = studentRepo.count();
		return count;
	}

	@Override
	public long CountByStatus(String status)
	{
		long count = studentRepo.countByStatus(status);
		return count;
	}


	

}
