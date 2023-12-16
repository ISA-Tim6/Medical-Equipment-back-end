INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Trebinje', 'BiH', 12, 12, 'Njegoseva', '5');
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Ruma', 'Srbija', 20, 12, 'Duciceva', '12');
INSERT INTO public.address(
	address_id, city, country, latitude, longitude, street, street_number)
	VALUES (default, 'Nevesinje', 'BiH', 11, 12, 'Beogradska', '7');
	
	
INSERT INTO public.working_time_calendar(
	working_time_calendar_id)
	VALUES (default);
INSERT INTO public.working_time_calendar(
	working_time_calendar_id)
	VALUES (default);
INSERT INTO public.working_time_calendar(
	working_time_calendar_id)
	VALUES (default);
	
	
	
INSERT INTO public.company(
	company_id, average_grade, name, address_id, working_time_calendar_id, opening_hours, closing_hours)
	VALUES (default, 0, 'Prva kompanija', 1, 1, '8:00', '20:00');
INSERT INTO public.company(
	company_id, average_grade, name, address_id, working_time_calendar_id, opening_hours, closing_hours)
	VALUES (default, 5, 'Druga kompanija', 3, 2, '8:00', '20:00');
INSERT INTO public.company(
	company_id, average_grade, name, address_id, working_time_calendar_id, opening_hours, closing_hours)
	VALUES (default, 4, 'Treca kompanija', 2, 3,  '8:00', '20:00');
	
	
	
	
INSERT INTO public."user"(
	user_id, city, country, email, employment, info_about_institution, logged_before, name, password, phone_number, surname, username, is_active)
	VALUES (default, 'Trebinje', 'BiH', 'kovacevic.ra15.2020@uns.ac.rs', 2, 'No info', false, 'Anja', '$2a$10$e7XlFOO4M3on0lR25rgGa.RMg7gsVatulqO3CVurJyJNHNatlg4iG', '012345678', 'Ducic', 'anja',true); --lozinka anja
INSERT INTO public."user"(
	user_id, city, country, email, employment, info_about_institution, logged_before, name, password, phone_number, surname, username, is_active)
	VALUES (default, 'Ruma', 'Srbija', 'milicavujic2001@gmail.com', 0, 'No info', false, 'Milica', '$2a$10$ipZcyjzB32XgfhX.ib1EZezNK14F6DHC/TdzmBUDnnuWahuBZgUBe', '012345678', 'Vujic', 'milica', true); --lozinka milica
INSERT INTO public."user"(
	user_id, city, country, email, employment, info_about_institution, logged_before, name, password, phone_number, surname, username, is_active)
	VALUES (default, 'Nevesinje', 'BiH', 'ducic.ra5.2020@uns.ac.rs', 0, 'No info', false, 'Ivana', '$2a$10$N1N7lG1i9VXTw4i2mbzN1OwbfS5G04EcuQHAUkVgupo.FEhtGu1TS', '012345678', 'Kovacevic', 'ivana', true); --lozinka ivana
INSERT INTO public."user"(
	user_id, city, country, email, employment, info_about_institution, logged_before, name, password, phone_number, surname, username, is_active)
	VALUES (default, 'Trebinje', 'BiH', 'kovace2vic.ra15.2020@uns.ac.rs', 1, 'No info', false, 'Anja', '$2a$10$e7XlFOO4M3on0lR25rgGa.RMg7gsVatulqO3CVurJyJNHNatlg4iG', '012345678', 'Ducic', 'anja2',true); --lozinka anja


INSERT INTO public.registrated_user(
	category, penals, user_id)
	VALUES (0, 0, 1);
INSERT INTO public.company_admin(
	user_id, company_id)
	VALUES (2, 1);
INSERT INTO public.system_admin(
	user_id)
	VALUES (4);
	
	
INSERT INTO public.equipment(
	equipment_id, description, name, type,price,quantity)
	VALUES (default, 'Hirurske makaze', 'Oprema 1', 'Hirurska oprema',500,5);
INSERT INTO public.equipment(
	equipment_id, description, name, type,price,quantity)
	VALUES (default, 'Zavoj', 'Oprema 2', 'Zavoji',100,20);
	
INSERT INTO public.company_equipment(
	company_id, equipment_id)
	VALUES (1, 1);
INSERT INTO public.company_equipment(
	company_id, equipment_id)
	VALUES (1, 2);



INSERT INTO public.role (name) VALUES ('ROLE_REGISTRATED_USER');
INSERT INTO public.role (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO public.role (name) VALUES ('ROLE_COMPANY_ADMIN');

INSERT INTO public.user_role (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO public.user_role (user_id, role_id) VALUES (2, 3); -- admin-u dodeljujemo rolu COMPANY ADMIN
INSERT INTO public.user_role (user_id, role_id) VALUES (3, 1); -- user-u dodeljujemo rolu USER
INSERT INTO public.user_role (user_id, role_id) VALUES (4, 2); -- user-u dodeljujemo rolu ADMIN

INSERT INTO public.appointment(
	appointment_id, appointment_status, local_date, duration, "end", local_time, user_id)
	VALUES (default, 0, '12-22-2023', 60, '17:00:00', '16:00:00', 2);
INSERT INTO public.item(
	item_id, quantity)
	VALUES (default, 1);
	
INSERT INTO public.equipment_item(
	equipment_id, item_id)
	VALUES (1, 1);
	
INSERT INTO public.reservation(
	reservation_id, reservation_status, appointment_id)
	VALUES (default, 0, 1);
INSERT INTO public.reservation_item(
	reservation_id, item_id)
	VALUES (1, 1);

